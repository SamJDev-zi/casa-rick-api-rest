package com.casarick.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationResponseDTO {
    private Long id;
    private String description;
    private Double deposit;
    private Double balance;
    private String status;
    private int stock;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime expiration;
    private CustomerDTO customerDTO;
    private UserResponseDTO userDTO;
    private BranchDTO branchDTO;
    private List<InventoryResponseDTO> inventoriesDTO;
}
