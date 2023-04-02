package com.example.saiyanjinsports.Controller;

import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.Product.CreateProduct;
import com.example.saiyanjinsports.Payload.request.Product.DeleteProduct;
import com.example.saiyanjinsports.Payload.request.Product.UpdateProduct;
import com.example.saiyanjinsports.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id")long id){
        Response response = productService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody CreateProduct dto){
        Response response = productService.create(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody UpdateProduct dto){
        Response response = productService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Response> delete(@RequestBody DeleteProduct dto){
        Response response = productService.delete(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
