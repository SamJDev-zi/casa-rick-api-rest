package com.casarick.dto;

import java.time.LocalDateTime;

public class SaleDTO {
    private Long id;
    private String description;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long customerId;
    private Long userId;
    private Long branchId;
}
