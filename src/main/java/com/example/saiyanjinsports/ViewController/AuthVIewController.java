package com.example.saiyanjinsports.ViewController;

import com.example.saiyanjinsports.Entities.Role;
import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthVIewController {
    @Autowired
    UserRepository repository;
  @RequestMapping("/default")
  public String login(HttpServletRequest request) {
      String userName = request.getUserPrincipal().getName();
      User user = repository.findByEmail(userName).get();
      boolean check = false;
      for (Role role : user.getRoles()){
          if(role.getName().equals("ADMIN")){
              check = true;
          }
      }
      if(check){
          return "Admin/User/index";

      }
      for (Role role : user.getRoles()){
          if(role.getName().equals("USER")){
              check = true;
          }
      }
      if(check){
          return "web/index";
      }

      return "web/index";
  }

}
