package com.example.backendapp.DTOs;

import com.example.backendapp.entities.OrderQuantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String orderEmail;
    private String orderName;
    private String orderAdress;
    private String contactNumber;
//    private String orderStatus;
//    private Double orderAmount;
    private List<OrderQuantity> orderQuantityList;
}
