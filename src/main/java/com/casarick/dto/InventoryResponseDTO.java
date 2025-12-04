package com.casarick.dto;

import java.time.LocalDateTime;

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
