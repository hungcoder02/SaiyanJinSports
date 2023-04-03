package com.example.saiyanjinsports.Controller;

import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.Gender.CreateGender;
import com.example.saiyanjinsports.Payload.request.Gender.DeleteGender;
import com.example.saiyanjinsports.Payload.request.Gender.UpdateGender;
import com.example.saiyanjinsports.Payload.request.Img.CreateImg;
import com.example.saiyanjinsports.Payload.request.Img.DeleteImg;
import com.example.saiyanjinsports.Payload.request.Img.UpdateImg;
import com.example.saiyanjinsports.Service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saiyanjin/img")
public class ImagesController {
    @Autowired
    ImagesService imagesService;
    @GetMapping("/all")
    public ResponseEntity<Response> getAll(){
        Response response = imagesService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id")long id){
        Response response = imagesService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody CreateImg dto){
        Response response = imagesService.create(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody UpdateImg dto){
        Response response = imagesService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Response> delete(@RequestBody DeleteImg dto){
        Response response = imagesService.delete(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
