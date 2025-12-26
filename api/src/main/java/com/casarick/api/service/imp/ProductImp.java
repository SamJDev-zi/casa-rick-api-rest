package com.casarick.api.service.imp;

import com.casarick.api.dto.ProductRequestDTO;
import com.casarick.api.dto.ProductResponseDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.*;
import com.casarick.api.repository.*;
import com.casarick.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public Optional<ProductResponseDTO> getProductByIdAndCategory(Long id, Long categoryId) {
        Optional<Product> productOptional = productRepository.findByIdAndCategoryId(id, categoryId);
        return productOptional.map(Mapper::toDTO);
    }

    @Override
    public Optional<ProductResponseDTO> getProductByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        return product.map(Mapper::toDTO);
    }

    @Override
    public ProductResponseDTO createNewProduct(ProductRequestDTO requestDTO) {
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + requestDTO.getCategoryId()));
        Type type = typeRepository.findById(requestDTO.getTypeId())
                .orElseThrow(() -> new NotFoundException("Type not found with id: " + requestDTO.getTypeId()));
        Industry industry = industryRepository.findById(requestDTO.getIndustryId())
                .orElseThrow(() -> new NotFoundException("Industry not found with id: " + requestDTO.getIndustryId()));

        Color color = colorRepository.findById(requestDTO.getColorId())
                .orElseThrow(() -> new NotFoundException("Color not found with id: " + requestDTO.getColorId()));

        Product product = Mapper.toEntity(requestDTO, category, type, industry, color);

        return Mapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + requestDTO.getCategoryId()));
        Type type = typeRepository.findById(requestDTO.getTypeId())
                .orElseThrow(() -> new NotFoundException("Type not found with id: " + requestDTO.getTypeId()));
        Industry industry = industryRepository.findById(requestDTO.getIndustryId())
                .orElseThrow(() -> new NotFoundException("Industry not found with id: " + requestDTO.getIndustryId()));

        Color color = colorRepository.findById(requestDTO.getColorId())
                .orElseThrow(() -> new NotFoundException("Color not found with id: " + requestDTO.getColorId()));

        product.setName(requestDTO.getName());
        product.setCategory(category);
        product.setType(type);
        product.setIndustry(industry);
        product.setColor(color);

        Product updatedProduct = productRepository.save(product);

        return Mapper.toDTO(productRepository.save(updatedProduct));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}
