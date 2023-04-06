package com.example.backendapp.DTOs;

import com.example.backendapp.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailsDTO {
    private String brand;

    private String name;

    private String serialNumber;

    private double price;

    private String generalCategory;

    private String category;

    private String descriere;

    private int quantity;

    private int percentSale;

    private byte[] bytes;
    private Image image;
}
