package com.casarick.dto;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<Long> inventoriesId;
}
