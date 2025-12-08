package com.casarick.api.service;

import com.casarick.api.dto.ProductRequestDTO;
import com.casarick.api.dto.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    Optional<ProductResponseDTO> getProductByIdAndCategory(Long id, Long categoryId);
    Optional<ProductResponseDTO> getProductByName(String name);
    ProductResponseDTO createNewProduct(ProductRequestDTO requestDTO);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO);
    void deleteProduct(Long id);
}
