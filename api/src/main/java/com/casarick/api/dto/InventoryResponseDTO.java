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
public class InventoryResponseDTO {
    private Long id;
    private ProductResponseDTO productDTO;
    private Double costPrice;
    private Double salePrice;
    private int stock;
    private LocalDateTime created;
    private LocalDateTime updated;
    private BranchDTO branchDTO;
}
