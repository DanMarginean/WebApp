package com.example.backendapp.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Item")
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String serialNumber;

    @Column(name = "price")
    private double price;


    private String generalCategory;

    @Column(name = "category")
    private String category;


    private Date dateOfAdd;

    @Column(name = "descriere")
    private String descriere;

    @Column(name = "recenzie")
    private String recenzie;

    @Column(name = "quantity")
    private Integer quantity;

    private Integer percentSale;


    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;


}
