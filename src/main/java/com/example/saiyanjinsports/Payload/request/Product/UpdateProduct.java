package com.example.saiyanjinsports.Payload.request.Product;

import lombok.Data;

@Data
public class UpdateProduct {
    private long id;
    private String name;
    private float price;
    private String description;
    private String information;
}
