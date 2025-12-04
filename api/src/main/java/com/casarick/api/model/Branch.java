package com.casarick.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branches")
@Builder
public class Branch {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "branch_id")
    private Long id;

    @Column(name = "branch_name")
    private String name;

    @Column(name = "branch_address")
    private String address;

    @Column(name = "branch_phone")
    private String phoneNumber;

    @Column(name = "is_active")
    private boolean isActive;
}
