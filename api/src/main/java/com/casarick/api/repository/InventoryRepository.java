package com.casarick.api.repository;

import com.casarick.api.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByStockGreaterThan(int stock);
}
