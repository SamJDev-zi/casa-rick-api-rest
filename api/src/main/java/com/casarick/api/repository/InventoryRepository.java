package com.casarick.api.repository;

import com.casarick.api.model.Branch;
import com.casarick.api.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByStockGreaterThan(int stock);

    List<Inventory> getAllByBranch(Branch branch);

    List<Inventory> getAllByCreatedAndBranch(LocalDateTime created, Branch branch);

    List<Inventory> findByCreatedBetweenAndBranch(LocalDateTime createdAfter, LocalDateTime createdBefore, Branch branch);
}
