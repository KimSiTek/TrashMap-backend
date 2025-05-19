package com.trashmap.backend.controller;

import com.trashmap.backend.entity.TrashBin;
import com.trashmap.backend.repository.TrashBinRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trashbins")
@CrossOrigin(origins = "*")
public class TrashBinController {

    private final TrashBinRepository repository;

    public TrashBinController(TrashBinRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TrashBin> getAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            e.printStackTrace(); // Railway Logs에 출력
            return List.of();    // 죽지 않게 fallback
        }
    }

}
