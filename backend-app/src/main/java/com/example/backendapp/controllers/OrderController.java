package com.example.backendapp.controllers;

import com.example.backendapp.DTOs.OrderDTO;
import com.example.backendapp.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private OrderService orderService;
@PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderDTO orderDTO){
        this.orderService.placeOrder(orderDTO);
    }
}
