package com.example.backendapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    private String filePath;
    private byte[] imageBytes;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id",insertable = false,updatable = false)
//    private Item item;



    // sa pun si bytes aici si sa trimit sa poate direct cu aia de downloar sa
    //o pun in service sul de get all items si dupa ce dau find all
    //sa aplic pe fiecare poza a itemului asta cu download dar cred ca si daca trimit bytes
    // in agular tot trebe sa transform din bytes in file
}