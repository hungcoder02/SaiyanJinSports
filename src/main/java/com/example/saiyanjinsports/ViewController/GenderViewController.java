package com.example.saiyanjinsports.ViewController;

import com.example.saiyanjinsports.Entities.Category;
import com.example.saiyanjinsports.Entities.Gender;
import com.example.saiyanjinsports.Repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gender")
public class GenderViewController {
    @Autowired
    private GenderRepository genderRepository;

    @RequestMapping("/")
    public String getAll(Model model){
        List<Gender> genders = genderRepository.findAll();
        model.addAttribute("genders", genders);
        return "/Admin/Gender/gender";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGender(Model model){
        model.addAttribute("gender", new Gender());
        return "/Admin/Gender/addGender";
    }
    @PostMapping("/add")
    public String createGender(@ModelAttribute("gender") Gender gender){
        genderRepository.save(gender);
        return "redirect:./";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editGender(@RequestParam("id")Long id, Model model){
        Gender editGender = genderRepository.findById(id).get();
        model.addAttribute("gender", editGender);
        return "/Admin/Gender/editGender";
    }
    @PostMapping("/edit")
    public String updateGenders(@ModelAttribute("gender") Gender gender){
        genderRepository.save(gender);
        return "redirect:./";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteGender(@RequestParam("id") Long id, Model model){
        Gender deleteGender = genderRepository.findById(id).get();
        genderRepository.delete(deleteGender);
        return "redirect:./";
    }
}
