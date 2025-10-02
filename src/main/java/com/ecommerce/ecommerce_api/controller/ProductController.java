package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.service.FileStorageService;
import com.ecommerce.ecommerce_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    @Autowired
    private FileStorageService fileStorageService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 🔹 GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // 🔹 GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // 🔹 CREATE PRODUCT (version avec upload d'image)
    @PostMapping
    public ResponseEntity<ProductDTO> createProductWithImage(
            @RequestPart("product") ProductDTO productDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        // ✅ Sauvegarde de l'image si elle existe
//        if (image != null && !image.isEmpty()) {
//            String imageUrl = saveImage(image);
//            productDTO.setImageUrl(imageUrl);
//        }

        // ✅ Création du produit via le service
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // 🔹 CREATE PRODUCT (version simple pour formulaire)
    @PostMapping("/simple")
    public ResponseEntity<ProductDTO> createSimpleProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String filename = fileStorageService.saveFile(file); // juste le nom du fichier
            String filePath = filename; // chemin relatif
            return ResponseEntity.ok(filePath);       // ⚡ uniquement le chemin
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur: " + e.getMessage());
        }
    }

    // 🔹 UPDATE PRODUCT (version avec upload d'image)
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductWithImage(
            @PathVariable Long id,
            @RequestPart("product") ProductDTO productDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imageUrl = saveImage(image);
            productDTO.setImageUrl(imageUrl);
        }

        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // 🔹 DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 METHODE PRIVEE POUR SAUVEGARDER LES IMAGES
    private String saveImage(MultipartFile image) throws IOException {
        String uploadDir = "src/main/resources/static/images/";
        String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        File file = new File(uploadDir + filename);

        // Crée le dossier s’il n’existe pas
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        image.transferTo(file);
        return "/images/" + filename;
    }
}
