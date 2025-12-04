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
@Table(name = "sales_detail")
@Builder
public class SaleDetail {
    @EmbeddedId // Mapea la clave primaria compuesta
    private SaleDetailId id;

    @ManyToOne
    @MapsId("saleId") // Mapea al campo 'saleId' dentro de SaleDetailId
    @JoinColumn(name = "sale_id") // Nombre de la columna en la tabla SQL
    private Sale sale; // Relación con la cabecera de la venta

    @ManyToOne
    @MapsId("inventoryId") // Mapea al campo 'inventoryId' dentro de SaleDetailId
    @JoinColumn(name = "inventory_id") // Nombre de la columna en la tabla SQL
    private Inventory inventory; // Relación con el producto/inventario

    @Column(name = "sale_amount")
    private Double saleAmount;

    @Column(name = "sale_discount")
    private Double saleDiscount;

    @Column(name = "sale_total")
    private Double saleTotal;
}
