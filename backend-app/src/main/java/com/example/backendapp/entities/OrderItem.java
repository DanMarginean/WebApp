package com.example.backendapp.entities;

import com.example.backendapp.Security.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;
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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "_user_id")
    private User user;
    private Integer quantity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> orderDetail;

    @PreRemove
    private void preRemove(){
        for (OrderDetail orderDetail1 : orderDetail){
//            orderDetail1.removeOrderItem(this);
            orderDetail1.setOrderItems(null);
        }

    }
}
