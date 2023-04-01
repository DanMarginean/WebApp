package com.example.backendapp.services;

import com.example.backendapp.DTOs.CartDTO;
import com.example.backendapp.Security.User;
import com.example.backendapp.config.JwtAuthenticationFilter;
import com.example.backendapp.entities.Cart;
import com.example.backendapp.entities.Item;
import com.example.backendapp.repositories.CartRepository;
import com.example.backendapp.repositories.ItemRepository;
import com.example.backendapp.repositories.UserRepository;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartService {

    private ItemService itemService;
    private JwtService jwtService;
    private CartRepository cartRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private  UserDetailsService userDetailsService;
    public final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public CartService(ItemService itemService,
                       JwtService jwtService,
                       CartRepository cartRepository,
                       ItemRepository itemRepository,
                       UserRepository userRepository,
                       UserDetailsService userDetailsService) {
        this.itemService = itemService;
        this.jwtService = jwtService;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    public CartDTO addToCart(UUID itemId, Authentication authentication) {
        Item item = this.itemRepository.findById(itemId).get();
        User user = this.userRepository.findByEmail(authentication.getName()).get();
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());
//        if (item != null && user != null) {
            CartDTO cartDTO = new CartDTO(item,user);
            Cart cart = this.modelMapper.map(cartDTO,Cart.class);
            this.cartRepository.save(cart);

//        }
    return cartDTO;
    }
}
