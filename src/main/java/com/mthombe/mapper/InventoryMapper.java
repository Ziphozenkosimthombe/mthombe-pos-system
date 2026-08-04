package com.mthombe.mapper;

import com.mthombe.modal.Branch;
import com.mthombe.modal.Inventory;
import com.mthombe.modal.Product;
import com.mthombe.payload.dto.InventoryDTO;
import com.mthombe.mapper.ProductMapper;

public class InventoryMapper {
    public static InventoryDTO toDTO(Inventory inventory) {
        return InventoryDTO.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productId((inventory.getProduct().getId()))
                .product(ProductMapper.toDTO(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .build();
    }
    public static Inventory toEntity(
            InventoryDTO inventoryDTO,
            Branch branch,
            Product product) {
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDTO.getQuantity())
                .build();
    }
}
