package com.example.saiyanjinsports.Service;

import com.example.saiyanjinsports.Entities.Gender;
import com.example.saiyanjinsports.Model.GenderDTO;
import com.example.saiyanjinsports.Model.Response;
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
}
