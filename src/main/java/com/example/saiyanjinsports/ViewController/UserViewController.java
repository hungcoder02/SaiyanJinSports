package com.example.saiyanjinsports.ViewController;

import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Repository.UserRepository;
import com.example.saiyanjinsports.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserViewController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String getAll(Model model){
        List<User> users = (List<User>) userService.getAll().getData();
        model.addAttribute("users", users);
        return "/Admin/User/index";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addUser(Model model){
        model.addAttribute("user", new User() );
        return "/Admin/User/addUser";
    }
    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") User user){
        userRepository.save(user);
        return "redirect:./";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long id, Model model){
      User userEdit = userRepository.findById(id).get();
      model.addAttribute("user",userEdit);
      return "/Admin/User/editUser";
    }
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user){
        userRepository.save(user);
        return "redirect:./";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id")Long id, Model model){
        User userDelete = userRepository.findById(id).get();
        userRepository.delete(userDelete);
        return "redirect:./";
    }
}
