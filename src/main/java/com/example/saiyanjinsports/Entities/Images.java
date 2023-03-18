package com.example.saiyanjinsports.Entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Images {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url")
    private String url;

}
