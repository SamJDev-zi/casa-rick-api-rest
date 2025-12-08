package com.casarick.api.service.imp;

import com.casarick.api.dto.CategoryDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Category;
import com.casarick.api.repository.CategoryRepository;
import com.casarick.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImp implements CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));

        return Mapper.toDTO(category);
    }

    @Override
    public Optional<CategoryDTO> getCategoryByName(String name) {
        Optional<Category> category = repository.findByName(name);
        return category.map(Mapper::toDTO);
    }

    @Override
    public CategoryDTO createNewCategory(Category category) {
        return Mapper.toDTO(repository.save(category));
    }
}
