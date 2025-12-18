package com.casarick.api.controller;

import com.casarick.api.dto.CategoryDTO;
import com.casarick.api.model.Category;
import com.casarick.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Optional<CategoryDTO>> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getCategoryByName(name));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createNewCategory(@RequestBody Category category) {
        CategoryDTO categoryDTO = service.createNewCategory(category);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/categories/{name}")
                .buildAndExpand(category.getName())
                .toUri();

        return ResponseEntity.created(location).body(categoryDTO);
        //return ResponseEntity.created(URI.create("/api/categories/" + categoryDTO.getName())).body(categoryDTO);
    }
}
