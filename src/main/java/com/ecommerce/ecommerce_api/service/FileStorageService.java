package com.ecommerce.ecommerce_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileStorageService {

    @Value("${file.upload-dir:src/main/resources/static/images/}")
    private String uploadDir;

    public String saveFile(MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File destination = new File(uploadDir + filename);

        // ✅ Crée le dossier si nécessaire
        if (!destination.getParentFile().exists()) {
            destination.getParentFile().mkdirs();
        }

        // ✅ Sauvegarde réelle
        file.transferTo(destination);

        // ✅ Retourne l’URL de l’image
        return "/images/" + filename;
    }
}
