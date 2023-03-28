package com.example.saiyanjinsports.Controller;

import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saiyanjin/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<Response> getAll(){
        Response response = productService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
