package com.example.saiyanjinsports.ViewController;

import com.example.saiyanjinsports.Entities.Role;
import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthVIewController {
    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/sign-in")
    public String signIn(Model model){
        model.addAttribute("user", new User() );
        return "/Login/signin";
    }
    @PostMapping("/sign-in")
    public String postSignIn(@ModelAttribute("user") User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "redirect:./login";
    }


    @GetMapping("/login")
    public String login(){
        return "Login/login";
    }
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
