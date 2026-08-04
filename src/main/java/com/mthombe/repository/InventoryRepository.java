package com.mthombe.repository;

import com.mthombe.modal.Inventory;
import com.mthombe.payload.dto.InventoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductIdAndBranchId(Long productId, Long branchId);
    List<Inventory> findByBranchId(Long branchId);
}
