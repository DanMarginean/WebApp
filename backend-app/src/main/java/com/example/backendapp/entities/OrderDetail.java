package com.example.backendapp.entities;

import com.example.backendapp.Security.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orderDetail")

public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;
    private String orderEmail;
    private String orderFirstName;
    private String orderLastName;
    private String orderAdress;
    private String contactNumber;
    private String orderStatus;
    private Double orderAmount;
    private Date orderDate;

    private Double shipping;

    @ManyToOne
    @JoinColumn(name = "_user_id")
    private User user;
    @ManyToMany
    @JoinColumn(name = "order_item_id")
    private List<OrderItem> orderItems;
}
