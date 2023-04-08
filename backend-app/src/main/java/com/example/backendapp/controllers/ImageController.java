package com.example.backendapp.controllers;

import com.example.backendapp.entities.Image;
import com.example.backendapp.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity uploadImage( @RequestPart("image") MultipartFile file) throws IOException{
        Image uploadImage = imageService.uploadImageToFile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage.getFilePath());
    }
@GetMapping("/download/{fileName}")
    public ResponseEntity downloiadImageFromFile (@PathVariable String fileName) throws IOException{
        byte[] imageData = imageService.downloadImageFromFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
