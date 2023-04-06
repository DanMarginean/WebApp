package com.example.backendapp.DTOs;

import com.example.backendapp.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String orderEmail;
    private String orderFirstName;
    private String orderLastName;

    private String orderAdress;
    private String contactNumber;

}
