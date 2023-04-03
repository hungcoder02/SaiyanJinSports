package com.example.saiyanjinsports.Service;

import com.example.saiyanjinsports.Entities.Gender;
import com.example.saiyanjinsports.Entities.Images;
import com.example.saiyanjinsports.Model.GenderDTO;
import com.example.saiyanjinsports.Model.ImgDTO;
import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.Gender.CreateGender;
import com.example.saiyanjinsports.Payload.request.Gender.DeleteGender;
import com.example.saiyanjinsports.Payload.request.Gender.UpdateGender;
import com.example.saiyanjinsports.Payload.request.Img.CreateImg;
import com.example.saiyanjinsports.Payload.request.Img.DeleteImg;
import com.example.saiyanjinsports.Payload.request.Img.UpdateImg;
import com.example.saiyanjinsports.Repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImagesService {
    @Autowired
    ImagesRepository imagesRepository;

    public Response getAll(){
        Response response = new Response();
        try {
            List<Images> images =imagesRepository.findAll();
            List<ImgDTO> imgDTOS = new ArrayList<>();
            for (Images i : images){
                ImgDTO imgDTO = new ImgDTO();
                imgDTO.setId(i.getId());
                imgDTO.setUrl(i.getName());
            }
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(imgDTOS);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
    public Response getOne(long id){
        Response response = new Response();
        try {
            Images images = imagesRepository.findById(id).orElse(null);
            if(images == null){
                response.setStatus(400);
                response.setMessage("Cannot found");
                return response;
            }
            ImgDTO imgDTO = new ImgDTO();
            imgDTO.setId(imgDTO.getId());
            imgDTO.setUrl(imgDTO.getUrl());
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(imgDTO);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
    public Response create(CreateImg dto){
        Response response = new Response();
        try {
            Images images = imagesRepository.findByName(dto.getUrl()).orElse(null);
            if (images == null){
                images = new Images();
                images.setName(dto.getUrl());
                imagesRepository.save(images);
                response.setStatus(200);
                response.setMessage("Ok");
                return response;
            }else {
                response.setStatus(400);
                response.setMessage("Already Exists");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
    public Response update(UpdateImg dto){
        Response response = new Response();
        try {
            Images images = imagesRepository.findById(dto.getId()).orElse(null);
            if (images != null){
                Images check = imagesRepository.findByName(dto.getUrl()).orElse(null);
                if (check == null){
                    images.setName(dto.getUrl());
                    imagesRepository.save(images);
                    response.setStatus(200);
                    response.setMessage("OK");
                    return response;
                }else {
                    response.setStatus(400);
                    response.setMessage("Product Already Exist");
                    return response;
                }
            }else {
                response.setStatus(400);
                response.setMessage("Product Already Exist");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("OMG!");
            response.getError().add(e.getMessage());
            return response;
        }
    }
    public Response delete(DeleteImg dto){
        Response response = new Response();
        try {
            Images images = imagesRepository.findById(dto.getId()).orElse(null);
            if (images != null){
                images.setId(images.getId());
                imagesRepository.delete(images);
                response.setStatus(200);
                response.setMessage("Deleted");
                return response;
            }else {
                response.setStatus(400);
                response.setMessage("Does not exist");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("OMG!");
            return response;
        }
    }
}
