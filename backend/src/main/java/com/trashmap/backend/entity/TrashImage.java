package com.trashmap.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class TrashImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagePath;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    // 기본 생성자
    public TrashImage() {}

    // getter
    public Long getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    // setter
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
