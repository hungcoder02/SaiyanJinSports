package com.example.saiyanjinsports.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private long id;
    private String email;
    private String phone;
    private String address;
    private List<String> role = new ArrayList<>();
}
