package com.example.saiyanjinsports.Service;

import com.example.saiyanjinsports.Entities.Role;
import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Model.UserDTO;
import com.example.saiyanjinsports.Payload.request.User.CreateUser;
import com.example.saiyanjinsports.Payload.request.User.DeleteUser;
import com.example.saiyanjinsports.Payload.request.User.UpdateUser;
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
    public Response getOne(long id){
        Response response = new Response();
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null ){
                response.setStatus(404);
                response.setMessage("not found");
                return response;
            }
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            userDTO.setAddress(user.getAddress());
            //role
            for (Role r : user.getRoles()){
                userDTO.getRole().add(r.getName());
            }
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(userDTO);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }

    public Response create(CreateUser createUser){
        Response response = new Response();
        try {
            User user = userRepository.findByEmail(createUser.getEmail()).orElse(null);
            if (user == null) {
                user = new User();
                user.setEmail(createUser.getEmail());
                userRepository.save(user);
                response.setStatus(200);
                response.setMessage("Created");
                return response;
            } else {
                response.setStatus(400);
                response.setMessage("User already exist");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Contact to admin for more information!");
            response.getError().add(e.getMessage());
            return response;
        }
    }

    public Response update(UpdateUser dto){
        Response response = new Response();
        try {
            User user = userRepository.findById(dto.getId()).orElse(null);
            if (user != null){
                User check = userRepository.findByEmail(dto.getEmail()).orElse(null);
                if(check == null){
                    user.setEmail(dto.getEmail());
                    user.setPassword(dto.getPassword());
                    user.setPhone(dto.getPhone_name());
                    user.setAddress(dto.getAddress());
                    userRepository.save(user);
                    response.setStatus(200);
                    response.setMessage("Update");
                    return response;
                }else {
                    response.setStatus(400);
                    response.setMessage("User already exists");
                    return response;
                }
            }else {
                response.setStatus(400);
                response.setMessage("User does not exists");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Contact to admin for more information!");
            response.getError().add(e.getMessage());
            return response;
        }
    }
    public Response delete(DeleteUser dto){
        Response response = new Response();
        try{
            User user = userRepository.findById(dto.getId()).orElse(null);
            if(user != null){
                user.setId(dto.getId());
                userRepository.delete(user);
                response.setStatus(200);
                response.setMessage("Deleted");
                return response;
            }else {
                response.setStatus(400);
                response.setMessage("User does not exists");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Contact to admin for more information!");
            response.getError().add(e.getMessage());
            return response;
        }
    }
}
