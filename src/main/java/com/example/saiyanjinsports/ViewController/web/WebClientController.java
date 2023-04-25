package com.example.saiyanjinsports.ViewController.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view-home")
public class WebClientController {

    @GetMapping("/")
    public String index(){
        return "web/index";
    }

    @GetMapping("/about")
    public String about(){
        return "web/about";
    }
    @GetMapping("/contact")
    public String contact(){
        return "web/contact";
    }
}
