package com.trashmap.backend.controller;

import com.trashmap.backend.entity.TrashImage;
import com.trashmap.backend.repository.TrashImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ImageUploadController {

    @Autowired
    private TrashImageRepository trashImageRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = new File("src/main/resources/static/uploads").getAbsolutePath();
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            TrashImage image = new TrashImage();
            image.setImagePath("/uploads/" + fileName);
            trashImageRepository.save(image);

            return ResponseEntity.ok("/uploads/" + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("업로드 실패");
        }
    }
    @GetMapping("/images")
    public List<String> getImagePath() {
        return trashImageRepository.findAll()
                .stream()
                .map(TrashImage::getImagePath)
                .collect(Collectors.toList());
    }
}


