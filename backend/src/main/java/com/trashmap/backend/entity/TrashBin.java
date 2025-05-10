package com.trashmap.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TrashBin {
    @Id
    private String id;
    private double lat;
    private double lng;
    private String status;
    private String description;
    private String imageUrl;
}
<button onClick={() => setShowUpload(true)}>쓰레기통 상태 신고</button>


