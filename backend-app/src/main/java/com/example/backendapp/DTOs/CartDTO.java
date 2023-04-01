package com.example.backendapp.DTOs;

import com.example.backendapp.Security.User;
import com.example.backendapp.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Item item;

    private User user;


}
