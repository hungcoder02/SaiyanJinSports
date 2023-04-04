package com.example.saiyanjinsports.Controller;

import com.example.saiyanjinsports.Payload.request.Login;
import com.example.saiyanjinsports.sencurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login){
        return ResponseEntity.status(200).body(authService.login(login));
    }
}
