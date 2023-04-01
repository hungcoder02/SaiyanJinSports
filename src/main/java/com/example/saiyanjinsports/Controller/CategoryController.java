package com.example.saiyanjinsports.Controller;

import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.CreateCategory;
import com.example.saiyanjinsports.Payload.request.DeleteCategory;
import com.example.saiyanjinsports.Payload.request.UpdateCategory;
import com.example.saiyanjinsports.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saiyanjin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<Response> getAll(){
        Response response = categoryService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id")long id){
        Response response = categoryService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody CreateCategory dto) {
        Response response = categoryService.create(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody UpdateCategory dto){
        Response response = categoryService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> delete(@RequestBody DeleteCategory dto){
        Response response = categoryService.delete(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

