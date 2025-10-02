package com.ecommerce.ecommerce_api.dto;

import com.ecommerce.ecommerce_api.dto.CategoryDTO;

public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private int quantity;
    private String imageUrl; // <-- nouvel attribut
    private CategoryDTO category;
    private String description;
    private String imageBase64;

    public ProductDTO() {}

    public ProductDTO(Long id, String name, Double price, int quantity, CategoryDTO category, String imageUrl, String imageBase64, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageBase64 = imageBase64;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public CategoryDTO getCategory() { return category; }
    public void setCategory(CategoryDTO category) { this.category = category; }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescription() {return description;}

    public void setDescription(String description) {
        this.description = description;
    }
}
