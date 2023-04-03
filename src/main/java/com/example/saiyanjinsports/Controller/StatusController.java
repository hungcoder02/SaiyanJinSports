package com.example.saiyanjinsports.Controller;

import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.Product.CreateProduct;
import com.example.saiyanjinsports.Payload.request.Product.DeleteProduct;
import com.example.saiyanjinsports.Payload.request.Product.UpdateProduct;
import com.example.saiyanjinsports.Payload.request.Status.CreateStatus;
import com.example.saiyanjinsports.Payload.request.Status.DeleteStatus;
import com.example.saiyanjinsports.Payload.request.Status.UpdateStatus;
import com.example.saiyanjinsports.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saiyanjin/status")
public class StatusController {
    @Autowired
    StatusService statusService;

    public ResponseEntity<Response> getAll(){
        Response response = statusService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id")long id){
        Response response = statusService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody CreateStatus dto){
        Response response = statusService.create(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody UpdateStatus dto){
        Response response = statusService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Response> delete(@RequestBody DeleteStatus dto){
        Response response = statusService.delete(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
