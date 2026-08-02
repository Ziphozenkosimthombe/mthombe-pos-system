package com.mthombe.repository;

import com.mthombe.modal.Branch;
import com.mthombe.payload.dto.BranchDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository  extends JpaRepository<Branch, Long> {
    List<Branch> findByStoreId(Long storeId);
}
