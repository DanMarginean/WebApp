package com.example.backendapp.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orderDetail")

public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private String orderEmail;
    private String orderName;
    private String orderAdress;
    private String contactNumber;
    private String orderStatus;
    private Double orderAmount;
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
