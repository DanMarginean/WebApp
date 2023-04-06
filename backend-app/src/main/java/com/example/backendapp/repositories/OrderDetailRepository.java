package com.example.backendapp.repositories;

import com.example.backendapp.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findAllByUserId(Integer id);

}
