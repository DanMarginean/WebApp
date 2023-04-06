package com.example.backendapp.services;

import com.example.backendapp.DTOs.OrderDTO;
import com.example.backendapp.entities.OrderDetail;
import com.example.backendapp.entities.OrderItem;
import com.example.backendapp.repositories.CartRepository;
import com.example.backendapp.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    public final ModelMapper modelMapper = new ModelMapper();

    public void placeOrder(OrderDTO orderDTO) {
        List<OrderItem> orderItemList = orderDTO.getOrderItemList();
        orderItemList.forEach(orderItem -> {

            OrderDetail orderDetail = this.modelMapper.map(orderDTO, OrderDetail.class);

        });
    }

    public void saveOrder(){

    }
}
