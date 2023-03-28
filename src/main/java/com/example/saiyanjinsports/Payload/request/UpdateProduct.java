package com.example.saiyanjinsports.Payload.request;

import lombok.Data;

@Data
public class UpdateProduct {
    private long id;
    private String name;
    private float price;
    private String description;
    private String information;
}
