package com.example.backendapp.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Item[] items;

    private double total;

    private String billingAdress;

    private String shippingAdress;

    private Date dateOfPurchase;


}
