package com.example.saiyanjinsports.Payload.request;

import lombok.Data;

@Data
public class UpdateUser {
    private long id;

    private String email;
    private String password;
    private String phone_name;
    private String address;
}
