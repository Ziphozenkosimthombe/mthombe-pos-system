package com.mthombe.payload.dto;

import com.mthombe.modal.Branch;
import com.mthombe.modal.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {
    private Long id;

    private Branch branch;

    private Long branchId;
    private Long productId;

    private ProductDTO product;

    private Integer quantity;

    private LocalDateTime lastUpdate;
}
