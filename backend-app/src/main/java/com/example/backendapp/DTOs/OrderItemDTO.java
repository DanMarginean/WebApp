package com.example.backendapp.DTOs;

import com.example.backendapp.Security.User;
import com.example.backendapp.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Item item;
    private UserDTO userDTO;
    private Integer quantity;
}
