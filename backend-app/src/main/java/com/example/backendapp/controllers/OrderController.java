package com.example.backendapp.controllers;

import com.example.backendapp.DTOs.OrderDTO;
import com.example.backendapp.entities.OrderDetail;
import com.example.backendapp.entities.OrderItem;
import com.example.backendapp.services.OrderDetailService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")

public class OrderController {
    private OrderDetailService orderDetailService;

    public OrderController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/placeOrder")
    @Transactional
    public OrderDetail placeOrder(@RequestBody OrderDTO orderDTO) {
        return this.orderDetailService.placeOrder(orderDTO);
    }

    @GetMapping("/checkout")
    public List<OrderItem> checkout() {

        return this.orderDetailService.checkout();
    }

    @GetMapping("/getAllOrders")
    public List<OrderDetail> getAllOrders() {
        return this.orderDetailService.getAllOrders();
    }

    @GetMapping("/getAllOrdersByUser")
    public List<OrderDetail> getAllOrdersByUser() {
        return this.orderDetailService.getAllOrdersByUser();
    }
}
