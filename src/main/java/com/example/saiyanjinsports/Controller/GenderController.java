package com.example.saiyanjinsports.Controller;

import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.Gender.CreateGender;
import com.example.saiyanjinsports.Payload.request.Gender.DeleteGender;
import com.example.saiyanjinsports.Payload.request.Gender.UpdateGender;
import com.example.saiyanjinsports.Service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saiyanjin/gender")
public class GenderController {
    @Autowired
    GenderService genderService;
    @GetMapping("/all")
    public ResponseEntity<Response> getAll(){
        Response response = genderService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id")long id){
        Response response = genderService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody CreateGender dto){
        Response response = genderService.create(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody UpdateGender dto){
        Response response = genderService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Response> delete(@RequestBody DeleteGender dto){
        Response response = genderService.delete(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
