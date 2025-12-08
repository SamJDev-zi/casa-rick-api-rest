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
public class SaleRequestDTO {
    private Long id;
    private String description;
    private int stock;
    private Double saleAmount;
    private Double saleDiscount;
    private Double saleTotal;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long customerId;
    private Long userId;
    private Long branchId;
    private Long InventoryId;
}
