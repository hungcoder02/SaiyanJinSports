package com.example.saiyanjinsports.Service;

import com.example.saiyanjinsports.Entities.Role;
import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Model.UserDTO;
import com.example.saiyanjinsports.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public Response getAll(){
        Response response = new Response();
        try{
            List<User> users = userRepository.findAll();
            List<UserDTO> userDTOS = new ArrayList<>();
            for (User u : users){
                UserDTO userDTO = new UserDTO();
                userDTO.setId(u.getId());
                userDTO.setEmail(u.getEmail());
                userDTO.setPhone(u.getPhone());
                userDTO.setAddress(u.getAddress());
                for (Role r : u.getRoles()){
                    userDTO.getRole().add(r.getName());
                }
                userDTOS.add(userDTO);
            }
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(userDTOS);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
}
