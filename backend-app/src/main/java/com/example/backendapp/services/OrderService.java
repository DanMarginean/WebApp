package com.example.backendapp.services;

import com.example.backendapp.DTOs.OrderDTO;
import com.example.backendapp.entities.OrderDetail;
import com.example.backendapp.entities.OrderQuantity;
import com.example.backendapp.repositories.CartRepository;
import com.example.backendapp.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    public final ModelMapper modelMapper = new ModelMapper();

    public void placeOrder(OrderDTO orderDTO) {
        List<OrderQuantity> orderQuantityList = orderDTO.getOrderQuantityList();
        orderQuantityList.forEach(orderQuantity -> {

            OrderDetail orderDetail = this.modelMapper.map(orderDTO, OrderDetail.class);

        });
    }
}
