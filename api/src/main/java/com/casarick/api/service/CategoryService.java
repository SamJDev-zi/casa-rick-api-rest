package com.casarick.api.service;

import com.casarick.api.dto.CategoryDTO;
import com.casarick.api.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    Optional<CategoryDTO> getCategoryByName(String name);
    CategoryDTO createNewCategory(Category category);
}
