package com.example.saiyanjinsports.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {
    private int Status;
    private String Message;
    private Object Data;

    private List<String> Error = new ArrayList<>();
}
