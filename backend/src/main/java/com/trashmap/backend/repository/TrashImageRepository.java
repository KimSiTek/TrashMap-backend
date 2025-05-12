package com.trashmap.backend.repository;

import com.trashmap.backend.entity.TrashImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashImageRepository extends JpaRepository<TrashImage, Long> {
}
