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

    @ManyToOne(cascade = CascadeType.ALL)
//    @ManyToOne( cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)  //am sters inafara de many to one
//    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "item_id") //Sterge toate iteme care sunt in cart asa! Sa vad cum am facut sa sterg un cart de la buton si sa aplic tot aia si cand fac delete item daca il geseste
    //in cartul userului sa il stearga
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
