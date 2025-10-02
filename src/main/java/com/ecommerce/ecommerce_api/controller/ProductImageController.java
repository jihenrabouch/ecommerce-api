package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.entity.Product;
import com.ecommerce.ecommerce_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/product-images")
public class ProductImageController {

    private final Path rootLocation = Paths.get("uploads");

    @Autowired
    private ProductService productService;

    @PostMapping("/{productId}")
    public ResponseEntity<ProductDTO> uploadImage(
            @PathVariable Long productId,
            @RequestParam("file") MultipartFile file) {

        try {
            // 1. Vérifier le produit
            ProductDTO product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }

            // 2. Créer dossier si inexistant
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            // 3. Générer un nom unique
            String extension = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf("."));
            String filename = "prod_" + productId + "_" + System.currentTimeMillis() + extension;

            // 4. Sauvegarder le fichier
            Files.copy(file.getInputStream(), rootLocation.resolve(filename));

            // 5. Mettre à jour le produit
            product.setImageUrl(filename);
            ProductDTO updatedProduct = productService.updateProduct(productId, product);

            return ResponseEntity.ok(updatedProduct);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}