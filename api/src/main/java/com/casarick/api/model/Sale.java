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
@Table(name = "sales")
@Builder
public class Sale {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "sale_id")
    private Long Id;

    @Column(name = "sale_description")
    private String description;

    @Column(name = "stock")
    private int stock;

    @Column(name = "sale_amount")
    private Double amount;
    @Column(name = "sale_discount")
    private Double discount;
    @Column(name = "sale_total")
    private Double total;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customer_id"
    )
    private Customer customerId;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    private User userId;

    @ManyToOne
    @JoinColumn(
            name = "branch_id",
            referencedColumnName = "branch_id"
    )
    private Branch branchId;

    @ManyToOne
    @JoinColumn(
            name = "inventory_id",
            referencedColumnName = "inventory_id"
    )
    private Inventory inventory;
}
