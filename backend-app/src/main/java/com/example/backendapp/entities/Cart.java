package com.example.backendapp.entities;

import com.example.backendapp.Security.User;
import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Cart")
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne
    @JoinColumn(name = "_user_id")
    private User user;

    private Integer quantity;

    public Cart(Item item, User user) {
        this.item = item;
        this.user = user;
    }
//    private double total;

//    private String billingAdress;
//
//    private String shippingAdress;
//
//    private Date dateOfPurchase;


}
