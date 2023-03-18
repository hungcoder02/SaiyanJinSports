package com.example.saiyanjinsports.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "itemcart")
public class ItemCart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "shopping_id")
    private Shopping shopping;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}
