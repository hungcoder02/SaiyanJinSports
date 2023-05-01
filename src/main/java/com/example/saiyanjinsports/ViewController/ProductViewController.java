package com.example.saiyanjinsports.ViewController;

import com.example.saiyanjinsports.Entities.Category;
import com.example.saiyanjinsports.Entities.Gender;
import com.example.saiyanjinsports.Entities.Product;
import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Repository.CategoryRepository;
import com.example.saiyanjinsports.Repository.GenderRepository;
import com.example.saiyanjinsports.Repository.ProductRepository;
import com.example.saiyanjinsports.Service.FileService;
import com.example.saiyanjinsports.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @Autowired
    FileService fileService;

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
    public String createProduct(@RequestParam("file") MultipartFile file,@ModelAttribute("product") Product product,@RequestParam("category") String categoryId,@RequestParam("gender") String genderId) throws IOException {
        Category category = categoryRepository.findById(Long.valueOf(categoryId)).get();
        Gender gender = genderRepository.findById(Long.valueOf(genderId)).get();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM-dd-YYYY");
        LocalDate now = LocalDate.now();
        String fileName = String.format("%s%s%s",now.format(formatters),product.getName(),file.getOriginalFilename());
        fileService.saveFile(fileName,file);
        product.setCategory(category);
        product.setGender(gender);
        product.setImageName(fileName);
        productRepository.save(product);
        return "redirect:./";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(@RequestParam("id") Long id, Model model){
        model.addAttribute("listCategory",categoryRepository.findAll());
        model.addAttribute("listGender",genderRepository.findAll());
        Product productEdit = productRepository.findById(id).get();
        model.addAttribute("product",productEdit);
        return "/Admin/Product/addPro";
    }
    @PostMapping("/edit")
    public String updateProduct(@RequestParam("file") MultipartFile file,@ModelAttribute("product") Product productdto, @RequestParam("category") String categoryId, @RequestParam("gender")String genderId) throws IOException {
        Product product = productRepository.findById(productdto.getId()).get();
        fileService.deleteFile(product.getImageName());
        Category category = categoryRepository.findById(Long.valueOf(categoryId)).get();
        Gender gender = genderRepository.findById(Long.valueOf(genderId)).get();
        product.setCategory(category);
        product.setGender(gender);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM-dd-YYYY");
        LocalDate now = LocalDate.now();
        String fileName = String.format("%s%s%s",now.format(formatters),product.getName(),file.getOriginalFilename());
        fileService.saveFile(fileName,file);
        product.setCategory(category);
        product.setGender(gender);
        product.setImageName(fileName);
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
