package com.example.saiyanjinsports.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "gender_name")
    private String gender_name;

    @OneToMany
    @JoinColumn(name = "pro_id")
    private List<Product> product;
}
