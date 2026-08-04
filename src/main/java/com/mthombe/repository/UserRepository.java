package com.mthombe.repository;

import com.mthombe.domain.UserRole;
import com.mthombe.modal.Store;
import com.mthombe.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}
