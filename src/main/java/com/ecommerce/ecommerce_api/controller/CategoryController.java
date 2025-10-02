package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.CategoryDTO;
import com.ecommerce.ecommerce_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getCategories() {
        return categoryService.getAllCategories();
    }
}
