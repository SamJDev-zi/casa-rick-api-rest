package com.casarick.api.controller;

import com.casarick.api.dto.ProductRequestDTO;
import com.casarick.api.dto.ProductResponseDTO;
import com.casarick.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{id}/{categoryId}")
    public ResponseEntity<ProductResponseDTO> getProductByIdAndCategory(
            @PathVariable Long id,
            @PathVariable Long categoryId) {

        Optional<ProductResponseDTO> product = service.getProductByIdAndCategory(id, categoryId);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/{name}")
    public ResponseEntity<ProductResponseDTO> getProductByName(@PathVariable String name) {
        Optional<ProductResponseDTO> product = service.getProductByName(name);

        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createNewProduct(@RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO product = service.createNewProduct(requestDTO);

        return ResponseEntity.created(URI.create("/api/products/" + product.getName())).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.ok(service.updateProduct(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}