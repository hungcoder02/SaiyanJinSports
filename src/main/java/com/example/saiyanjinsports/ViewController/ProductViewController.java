package com.example.saiyanjinsports.ViewController;

import com.example.saiyanjinsports.Entities.Category;
import com.example.saiyanjinsports.Entities.Gender;
import com.example.saiyanjinsports.Entities.Product;
import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Repository.CategoryRepository;
import com.example.saiyanjinsports.Repository.GenderRepository;
import com.example.saiyanjinsports.Repository.ProductRepository;
import com.example.saiyanjinsports.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductViewController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String getAll(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "/Admin/Product/product";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProduct(Model model){
        model.addAttribute("listCategory",categoryRepository.findAll());
        model.addAttribute("listGender",genderRepository.findAll());
        model.addAttribute("product", new Product());
        return "/Admin/Product/addPro";
    }
    @PostMapping("/add")
    public String createProduct(@ModelAttribute("product") Product product,@RequestParam("category") String categoryId,@RequestParam("gender") String genderId){
        Category category = categoryRepository.findById(Long.valueOf(categoryId)).get();
        Gender gender = genderRepository.findById(Long.valueOf(genderId)).get();
        product.setCategory(category);
        product.setGender(gender);
        productRepository.save(product);
        return "redirect:./";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(@RequestParam("id") Long id, Model model){
        Product productEdit = productRepository.findById(id).get();
        model.addAttribute("product",productEdit);
        return "/Admin/Product/addPro";
    }
    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute("product") Product product, @RequestParam("category") String categoryId, @RequestParam("gender")String genderId){
        Category category = categoryRepository.findById(Long.valueOf(categoryId)).get();
        Gender gender = genderRepository.findById(Long.valueOf(genderId)).get();
        product.setCategory(category);
        product.setGender(gender);
        productRepository.save(product);
        return "redirect:./";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("id")Long id, Model model){
        Product Productdelete = productRepository.findById(id).get();
        productRepository.delete(Productdelete);
        return "redirect:./";
    }
}
