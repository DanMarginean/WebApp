package com.example.backendapp.entities;

import com.example.backendapp.Security.User;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "_user_id")
    private User user;
    private Integer quantity;

}
