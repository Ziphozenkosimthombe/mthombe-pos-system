package com.mthombe.service;

import com.mthombe.exceptions.UserException;
import com.mthombe.modal.User;
import com.mthombe.payload.dto.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO createBranch(BranchDTO branchDTO) throws UserException;
    BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception;
    void deleteBranch(Long id) throws Exception;
    List<BranchDTO> getAllBranchByStoreId(Long storeId);
    BranchDTO getBranchById(Long id) throws Exception;
}
