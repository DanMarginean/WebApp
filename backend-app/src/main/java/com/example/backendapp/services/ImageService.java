package com.example.backendapp.services;

import com.example.backendapp.entities.Image;
import com.example.backendapp.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    private final String PATH = "C:/Users/danma/Desktop/ImagePath/";

    public Image uploadImageToFile(MultipartFile file) throws IOException {

        String filePath = PATH + file.getOriginalFilename();
        Image image = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build());

        file.transferTo(new File(filePath));

        if (image != null) {
            return image;
        }
        return null;
    }

    public byte[] downloadImageFromFile(String fileName) throws IOException {
        Image image = imageRepository.findFirstByFilePath(fileName);
        String filePath = image.getFilePath();
        byte[] images = Files.readAllBytes(Paths.get(filePath));
        return images;
    }
}
