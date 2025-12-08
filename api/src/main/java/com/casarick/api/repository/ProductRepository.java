package com.casarick.api.repository;

import com.casarick.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getProductByBarCodeNumber(String barCodeNumber);
    Optional<Product> findByIdAndCategoryId(Long productId, Long categoryId);
    Optional<Product> findByName(String name);
}
