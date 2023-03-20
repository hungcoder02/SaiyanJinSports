package com.example.saiyanjinsports.Controller;

import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saiyanjin/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/all")
    public ResponseEntity<Response> getAll(){

        Response response = userService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
