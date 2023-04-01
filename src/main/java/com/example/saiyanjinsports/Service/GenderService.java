package com.example.saiyanjinsports.Service;

import com.example.saiyanjinsports.Entities.Gender;
import com.example.saiyanjinsports.Model.GenderDTO;
import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.CreateGender;
import com.example.saiyanjinsports.Repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenderService {
    @Autowired
    GenderRepository genderRepository;

    public Response getAll(){
        Response response = new Response();
        try {
            List<Gender> genders =genderRepository.findAll();
            List<GenderDTO> genderDTOS = new ArrayList<>();
            for (Gender g : genders){
                GenderDTO genderDTO = new GenderDTO();
                genderDTO.setId(g.getId());
                genderDTO.setName(g.getName());
            }
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(genderDTOS);
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
            Gender gender = genderRepository.findById(id).orElse(null);
            if(gender == null){
                response.setStatus(400);
                response.setMessage("Cannot found");
                return response;
            }
            GenderDTO genderDTO = new GenderDTO();
            genderDTO.setId(genderDTO.getId());
            genderDTO.setName(genderDTO.getName());
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(genderDTO);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
    public Response create(CreateGender dto){
        Response response = new Response();
        try {
            Gender gender = genderRepository.findByName(dto.getName()).orElse(null);
            if (gender == null){
                gender = new Gender();
                gender.setName(dto.getName());
                genderRepository.save(gender);
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
}
