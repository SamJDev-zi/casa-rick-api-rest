package com.casarick.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    private Long customerId;
    private Long userId;
    private Long branchId;
    private Long InventoryId;
}
