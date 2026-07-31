package com.mthombe.service;

import com.mthombe.exceptions.UserException;
import com.mthombe.payload.dto.UserDto;
import com.mthombe.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto);

}
