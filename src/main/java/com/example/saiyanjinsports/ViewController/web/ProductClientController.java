package com.example.saiyanjinsports.ViewController.web;

import com.example.saiyanjinsports.Entities.Product;
import com.example.saiyanjinsports.Payload.request.Product.ProductView;
import com.example.saiyanjinsports.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/view-product")
public class ProductClientController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/mens")
    public String getMenProducts(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "8") int size){
        Pageable pageable = PageRequest.of(page,size);
        ProductView  productView = new ProductView();
        Page<Product> p = productRepository.findMenProducts(pageable);
        productView.setProducts(p);
        productView.setTotal(p.getTotalPages());
        model.addAttribute("menProducts",productView);
        return "/web/mens";
    }
    @GetMapping("/womens")
    public String getWomenProducts(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "8") int size){
        Pageable pageable = PageRequest.of(page, size);
        ProductView productView = new ProductView();
        Page<Product> p = productRepository.finWomenProducts(pageable);
        productView.setProducts(p);
        productView.setTotal(p.getTotalPages());
        model.addAttribute("womenProducts", productView);
        return "/web/womens";
    }
    @GetMapping("/single")
    public String getSingleProducts(@RequestParam("id") Long id, Model model){
        Product productEdit = productRepository.findById(id).get();
        model.addAttribute("product", productEdit );
        return "web/single";
    }
}
