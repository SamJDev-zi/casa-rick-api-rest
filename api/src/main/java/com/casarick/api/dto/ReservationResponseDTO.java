package com.casarick.api.dto;

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
