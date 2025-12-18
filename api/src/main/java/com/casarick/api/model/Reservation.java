package com.casarick.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
@Builder
public class Reservation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "reservation_description")
    private String description;

    @Column(name = "reservation_deposit_amount")
    private Double deposit;

    @Column(name = "reservation_balance_due")
    private Double balance;

    @Column(name = "reservation_status")
    private String status;

    @Column(name = "stock")
    private int stock;

    @Column(name = "created_at")
    private LocalDateTime created;

    @Column(name = "updated_at")
    private LocalDateTime updated;

    @Column(name = "expiration_date")
    private LocalDateTime expiration;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customer_id"
    )
    private Customer customer;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "branch_id",
            referencedColumnName = "branch_id"
    )
    private Branch branch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "inventory_id",
            referencedColumnName = "inventory_id"
    )
    private Inventory inventory;
}
