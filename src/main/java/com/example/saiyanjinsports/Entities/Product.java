package com.example.saiyanjinsports.Entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "pro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pro_name")
    private String pro_name;

    @Column(name = "pro_price")
    private float price;

    @Column(name = "pro_des")
    private String description;

    @Column(name = "pro_infor")
    private String information;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
