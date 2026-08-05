package com.mthombe.service.impl;

import com.mthombe.configuration.JwtProvider;
import com.mthombe.domain.UserRole;
import com.mthombe.exceptions.UserException;
import com.mthombe.mapper.UserMapper;
import com.mthombe.modal.Branch;
import com.mthombe.modal.Store;
import com.mthombe.modal.User;
import com.mthombe.payload.dto.UserDto;
import com.mthombe.payload.response.AuthResponse;
import com.mthombe.repository.BranchRepository;
import com.mthombe.repository.StoreRepository;
import com.mthombe.repository.UserRepository;
import com.mthombe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;

    // ADD THESE
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;

    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {

        User user = userRepository.findByEmail(userDto.getEmail());

        if (user != null) {
            throw new UserException("email id already registered !");
        }

        if (userDto.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UserException("role admin is not allowed !");
        }

        User newUser = new User();

        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setFullName(userDto.getFullName());
        newUser.setPhone(userDto.getPhone());

        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        // SET STORE
        if (userDto.getStoreId() != null) {

            Store store = storeRepository.findById(userDto.getStoreId())
                    .orElseThrow(() ->
                            new RuntimeException("Store not found"));

            newUser.setStore(store);
        }

        // SET BRANCH
        if (userDto.getBranchId() != null) {

            Branch branch = branchRepository.findById(userDto.getBranchId())
                    .orElseThrow(() ->
                            new RuntimeException("Branch not found"));

            newUser.setBranch(branch);
        }

        User savedUser = userRepository.save(newUser);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully !");
        authResponse.setUser(UserMapper.toDTO(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();

        Authentication authentication = authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();

        String role = authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(email);

        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successfully !");
        authResponse.setUser(UserMapper.toDTO(user));

        return authResponse;
    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails =
                customUserImplementation.loadUserByUsername(email);

        if(userDetails == null) {
            throw new UsernameNotFoundException(
                    "email id doesn't exist !");
        }

        if (!passwordEncoder.matches(
                password,
                userDetails.getPassword()
        )) {

            throw new BadCredentialsException(
                    "password doesn't match!");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
