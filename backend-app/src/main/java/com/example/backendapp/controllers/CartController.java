package com.example.backendapp.controllers;

import com.example.backendapp.DTOs.CartDTO;
import com.example.backendapp.entities.Cart;
import com.example.backendapp.services.CartService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.List;
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
//    @PostMapping("/addCart/{id}")
    @GetMapping("addCart/{id}/{quantity}")
    public CartDTO addToCart(@PathVariable(name = "id") UUID id, @PathVariable(name = "quantity") Integer quantity, Authentication authentication)
    {
        return cartService.addToCart(id,quantity,authentication);
    }

    @GetMapping("/getCartDetails")
    public List<Cart> getCartDetails(){
        return cartService.getCartDetails();
    }

    @DeleteMapping("/deleteCart/{id}")
    public void deteleCart(@PathVariable(name = "id") Long id){
        this.cartService.deleteCart(id);

    }
}
