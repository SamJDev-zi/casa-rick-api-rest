package com.casarick.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailDTO {
    private Long inventoryId;
    private Double saleAmount;
    private Double saleDiscount;
    private Double saleTotal;
}
