package com.example.backendapp.services;

import com.example.backendapp.DTOs.CartDTO;
import com.example.backendapp.Security.User;
import com.example.backendapp.config.JwtAuthenticationFilter;
import com.example.backendapp.entities.Cart;
import com.example.backendapp.entities.Item;
import com.example.backendapp.repositories.CartRepository;
import com.example.backendapp.repositories.ItemRepository;
import com.example.backendapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class CartService {

    private ItemService itemService;
    private JwtService jwtService;
    private CartRepository cartRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private UserDetailsService userDetailsService;
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

    public CartDTO addToCart(UUID itemId, Integer quantity, Authentication authentication) {
        Item item = this.itemRepository.findById(itemId).get();
        User user = this.userRepository.findByEmail(authentication.getName()).get();
        CartDTO cartDTO = new CartDTO();
        if (quantity == null) {
            quantity = 1;
        }
        Cart cartc = this.cartRepository.findByItem(item);
        if (cartc != null) {
            if (cartc.getItem().getId().equals(item.getId())) {
                if (cartc.getQuantity() == null) {
                    cartc.setQuantity(1);
                }
                cartc.setQuantity(cartc.getQuantity() + quantity);
                cartDTO = this.modelMapper.map(cartc, CartDTO.class);
                this.cartRepository.save(cartc);
            }
        } else {
            cartDTO.setQuantity(quantity);
            cartDTO.setUser(user);
            cartDTO.setItem(item);
            Cart cart = this.modelMapper.map(cartDTO, Cart.class);
            this.cartRepository.save(cart);

        }
        return cartDTO;
    }

    public List<Cart> getCartDetails() {
        String userEmail = JwtAuthenticationFilter.userEmail;
        User user = this.userRepository.findByEmail(userEmail).get();
        List<Cart> cartList = this.cartRepository.findByUser(user);
        return cartList;
    }

    public void deleteCart(Long id) {
        this.cartRepository.deleteById(id);
    }
}
