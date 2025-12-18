package com.casarick.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequestDTO {
    private Long id;
    private String description;
    private Double deposit;
    private Double balance;
    private String status;
    private int stock;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime expiration;
    private Long customerId;
    private Long userId;
    private Long branchId;
    private Long inventoryId;
}
