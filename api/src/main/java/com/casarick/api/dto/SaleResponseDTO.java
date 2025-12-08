package com.casarick.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleResponseDTO {
    private Long id;
    private String description;
    private int stock;
    private Double saleAmount;
    private Double saleDiscount;
    private Double saleTotal;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CustomerDTO customerDTO;
    private UserResponseDTO userDTO;
    private BranchDTO branchDTO;
    private InventoryResponseDTO InventoryDTO;
}
