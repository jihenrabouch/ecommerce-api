package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.CategoryDTO;
import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.entity.Category;
import com.ecommerce.ecommerce_api.entity.Product;
import com.ecommerce.ecommerce_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // 🔹 GET ALL CATEGORIES
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // 🔹 MAPPING ENTITY → DTO
    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        List<ProductDTO> productDTOs = category.getProducts().stream()
                .map(this::mapProductToDTO)
                .collect(Collectors.toList());
        dto.setProducts(productDTOs);

        return dto;
    }

    private ProductDTO mapProductToDTO(Product product) {
        ProductDTO pdto = new ProductDTO();
        pdto.setId(product.getId());
        pdto.setName(product.getName());
        pdto.setPrice(product.getPrice());
        pdto.setQuantity(product.getQuantity());
        pdto.setImageUrl(product.getImageUrl());
        return pdto;
    }
}
