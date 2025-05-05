package com.trashmap.backend.repository;

import com.trashmap.backend.entity.TrashBin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashBinRepository extends JpaRepository<TrashBin, String> {}
