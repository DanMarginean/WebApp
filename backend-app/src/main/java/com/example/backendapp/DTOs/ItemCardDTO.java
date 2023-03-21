package com.example.backendapp.DTOs;


import com.example.backendapp.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCardDTO {
    private UUID id;

    private String name;

    private double price;

//    private String category;

    private String descriere;

    private String filePath;
    private byte[] bytes;
    private Image image;


}
