package com.casarick.dto;

import java.time.LocalDateTime;

public class InventoryRequestDTO {
    private Long id;
    private Long productId;
    private Double costPrice;
    private Double salePrice;
    private int stock;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long branchId;
}
