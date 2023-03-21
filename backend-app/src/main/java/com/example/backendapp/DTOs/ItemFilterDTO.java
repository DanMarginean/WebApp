package com.example.backendapp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemFilterDTO {

    private String brand;

    private String name;

    private double price;

    private String searchString;
}
