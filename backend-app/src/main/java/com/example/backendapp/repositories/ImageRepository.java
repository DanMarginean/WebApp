package com.example.backendapp.repositories;

import com.example.backendapp.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findFirstByFilePath(String filePath);
}
