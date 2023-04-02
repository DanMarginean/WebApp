package com.example.backendapp.repositories;

import com.example.backendapp.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail,Long> {
}
