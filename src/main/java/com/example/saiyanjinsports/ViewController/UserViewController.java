package com.example.saiyanjinsports.ViewController;

import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserViewController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String getAll(Model model){
        List<User> users = (List<User>) userService.getAll().getData();
        model.addAttribute("users", users);
        return "/Admin/User/index";
    }

}
