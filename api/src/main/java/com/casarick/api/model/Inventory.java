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
@Table(name = "inventories")
@Builder
public class Inventory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "inventory_id")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "product_id"
    )
    private Product product;

    @Column(name = "cost_price")
    private Double costPrice;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "stock")
    private int stock;

    @Column(name = "created_at")
    private LocalDateTime created;

    @Column(name = "updated_at")
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(
            name = "branch_id",
            referencedColumnName = "branch_id"
    )
    private Branch branch;
}
