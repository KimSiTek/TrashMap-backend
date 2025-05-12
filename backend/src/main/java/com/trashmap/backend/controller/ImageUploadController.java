package com.trashmap.backend.controller;

import com.trashmap.backend.entity.TrashImage;
import com.trashmap.backend.repository.TrashImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ImageUploadController {

    @Autowired
    private TrashImageRepository trashImageRepository;

    // 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 운영 환경에서 접근 가능한 임시 디렉토리
            String uploadDir = System.getProperty("java.io.tmpdir");

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            Files.write(filePath, file.getBytes());

            TrashImage image = new TrashImage();
            image.setImagePath(fileName); // 이제 전체 경로가 아닌 파일명만 저장
            trashImageRepository.save(image);

            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("업로드 실패");
        }
    }

    // 이미지 목록 조회
    @GetMapping("/images")
    public List<String> getImagePaths() {
        return trashImageRepository.findAll()
                .stream()
                .map(TrashImage::getImagePath)
                .collect(Collectors.toList());
    }

    // 실제 이미지 보기용 API
    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(System.getProperty("java.io.tmpdir"), filename);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
