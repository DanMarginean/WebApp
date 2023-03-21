package com.example.backendapp.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
    @GeneratedValue(strategy = GenerationType.AUTO) //item: id, name,price,brand, model,dateOfAdd,cantitate,invoiceNumber,category,descriere
    private UUID id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(nullable = false)
    private String name;

    @Column( nullable = false)
    private String serialNumber;

    @Column(name = "price")
    private double price;

//    @Column(name = "generalCategory")
    private String generalCategory;

    @Column(name = "category")
    private String category;

//    @Column(name = "dateOfAdd")
    private Date dateOfAdd;

    @Column(name = "descriere")
    private String descriere;

    @Column(name = "recenzie")
    private String recenzie; // tre sa fac entitate recenzie legata one to many

    @Column(name = "quantity")
    private String quantity;

//    @Column(name = "percentSale")
    private String percentSale;


//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "item",
//    joinColumns = {@JoinColumn(name = "item_id")},
//    inverseJoinColumns = {
//            @JoinColumn(name = "image_id")
//    }
//    )
//
//

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    private String filePath;

    //legare one to one cu cart entity

}
