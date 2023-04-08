package com.example.backendapp.repositories;

import com.example.backendapp.DTOs.CartDTO;
import com.example.backendapp.Security.User;
import com.example.backendapp.entities.Cart;
import com.example.backendapp.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    public List<Cart> findByUser(User user);

    public Cart findByItemAndUser(Item item ,User user);

    public List<Cart> findAllByItem_Id(UUID id);

    public void deleteAllByUser(User user);
}
