package com.casarick.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO {
    private Long id;
    private String name;
    private Long categoryId;
    private Long typeId;
    private Long industryId;
    private String color;
    private String size;
    private String photoUrl;
    private String barCodeNumber;

    //cambio xd XD
}
