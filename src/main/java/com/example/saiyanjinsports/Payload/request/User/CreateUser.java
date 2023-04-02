package com.example.saiyanjinsports.Payload.request.User;

import lombok.Data;

@Data
public class CreateUser {
    private String email;
    private String password;
    private String phone_name;
    private String address;
}

