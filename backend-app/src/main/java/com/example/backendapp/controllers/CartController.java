package com.example.backendapp.controllers;

import com.example.backendapp.entities.Cart;
import com.example.backendapp.services.CartService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }
    @PostMapping("/addCart/{id}")
    public void addToCart(@PathVariable UUID id, Authentication authentication)
    {


         cartService.addToCart(id,authentication);
    }
}
