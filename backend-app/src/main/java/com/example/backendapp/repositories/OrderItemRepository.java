package com.example.backendapp.repositories;

import com.example.backendapp.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    public List<OrderItem> findAllByItem_Id(UUID id);
}