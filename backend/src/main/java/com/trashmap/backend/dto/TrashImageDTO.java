package com.trashmap.backend.dto;

public class TrashImageDTO {
    private String imagePath;
    private String trashBinName;

    public TrashImageDTO(String imagePath, String trashBinName) {
        this.imagePath = imagePath;
        this.trashBinName = trashBinName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTrashBinName() {
        return trashBinName;
    }
}
