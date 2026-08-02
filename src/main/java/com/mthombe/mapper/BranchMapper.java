package com.mthombe.mapper;

import com.mthombe.modal.Branch;
import com.mthombe.modal.Store;
import com.mthombe.payload.dto.BranchDTO;
import lombok.Builder;

import java.time.LocalDateTime;

public class BranchMapper {

    public static BranchDTO toDTO(Branch branch) {
        return BranchDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getPhone())
                .closeTime(branch.getCloseTime())
                .openTime(branch.getOpenTime())
                .workingDays(branch.getWorkingDays())
                .storeId(branch.getStore()!=null?branch.getStore().getId():null)
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .build();
    }
    public  static Branch toEntity(BranchDTO branchDTO, Store store) {
        return Branch.builder()
                .name(branchDTO.getName())
                .address(branchDTO.getAddress())
                .store(store)
                .email(branchDTO.getEmail())
                .phone(branchDTO.getPhone())
                .closeTime(branchDTO.getCloseTime())
                .openTime(branchDTO.getOpenTime())
                .workingDays(branchDTO.getWorkingDays())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
