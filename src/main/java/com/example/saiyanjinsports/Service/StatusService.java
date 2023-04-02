package com.example.saiyanjinsports.Service;

import com.example.saiyanjinsports.Entities.Gender;
import com.example.saiyanjinsports.Entities.Status;
import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Model.StatusDTO;
import com.example.saiyanjinsports.Payload.request.Status.CreateStatus;
import com.example.saiyanjinsports.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;
    public Response getAll(){
        Response response = new Response();
        try {
            List<Status> statuses = statusRepository.findAll();
            List<StatusDTO> statusDTOS = new ArrayList<>();
            for (Status s : statuses){
                StatusDTO statusDTO = new StatusDTO();
                statusDTO.setId(s.getId());
                statusDTO.setName(s.getName());
            }
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(statusDTOS);
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
            Status status = statusRepository.findById(id).orElse(null);
            if (status == null){
                response.setStatus(404);
                response.setMessage("not found");
                return response;
            }
            StatusDTO statusDTO = new StatusDTO();
            statusDTO.setId(status.getId());
            statusDTO.setName(status.getName());
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(statusDTO);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
    public Response create(CreateStatus dto){
        Response response = new Response();
        try {
            Status status = statusRepository.findByName(dto.getName()).orElse(null);
            if (status == null){
                status = new Status();
                status.setName(dto.getName());
                statusRepository.save(status);
                response.setStatus(200);
                response.setMessage("Created");
                return response;
            }else {
                response.setStatus(400);
                response.setMessage("Exist");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
}
