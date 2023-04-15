package com.example.saiyanjinsports.ViewController;

import com.example.saiyanjinsports.Entities.Category;
import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Repository.CategoryRepository;
import com.example.saiyanjinsports.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryViewController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String getAll(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "/Admin/Category/category";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "/Admin/Category/addCate";
    }
    @PostMapping("/add")
    public String createCategory(@ModelAttribute("category") Category category ){
        categoryRepository.save(category);
        return "redirect:./";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editCategory(@RequestParam("id") Long id, Model model){
        Category categoryEdit = categoryRepository.findById(id).get();
        model.addAttribute("category",categoryEdit);
        return "/Admin/Category/addCate";
    }
    @PostMapping("/edit")
    public String updateCategory(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:./";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCategory(@RequestParam("id")Long id, Model model){
        Category categoryDelete = categoryRepository.findById(id).get();
        categoryRepository.delete(categoryDelete);
        return "redirect:./";
    }
}
