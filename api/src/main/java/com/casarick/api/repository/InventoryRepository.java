package com.casarick.api.repository;

import com.casarick.api.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByStockGreaterThan(int stock);

    List<Inventory> getAllByBranch(Branch branch);

    List<Inventory> getAllByCreatedAndBranch(LocalDateTime created, Branch branch);

    List<Inventory> findByCreatedBetweenAndBranch(LocalDateTime createdAfter, LocalDateTime createdBefore, Branch branch);

    @Query("SELECT i FROM Inventory i WHERE " +
            "(:category IS NULL OR i.product.category = :category) AND " +
            "(:type IS NULL OR i.product.Type = :type) AND " +
            "(:industry IS NULL OR i.product.industry = :industry)")
    List<Inventory> searchWithFilters(
            @Param("category") Category category,
            @Param("type") Type type,
            @Param("industry") Industry industry
    );
}
