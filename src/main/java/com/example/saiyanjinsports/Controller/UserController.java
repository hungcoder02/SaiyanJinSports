package com.example.saiyanjinsports.Controller;

import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.CreateUser;
import com.example.saiyanjinsports.Payload.request.DeleteUser;
import com.example.saiyanjinsports.Payload.request.UpdateUser;
import com.example.saiyanjinsports.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id")long id){
        Response response = userService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody CreateUser dto){
        Response response = userService.create(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody UpdateUser dto){
        Response response = userService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Response> delete(@RequestBody DeleteUser dto){
        Response response = userService.delete(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
