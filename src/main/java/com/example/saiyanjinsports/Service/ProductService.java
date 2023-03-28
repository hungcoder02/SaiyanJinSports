package com.example.saiyanjinsports.Service;

import com.example.saiyanjinsports.Entities.Product;
import com.example.saiyanjinsports.Model.ProductDTO;
import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Response getAll(){
        Response response = new Response();
        try{
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOS = new ArrayList<>();
            for (Product p : products){
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(p.getId());
                productDTO.setName(p.getPro_name());
                productDTO.setPrice(p.getPrice());
                productDTO.setDescription(p.getDescription());
                productDTO.setInformation(p.getInformation());
            }
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(productDTOS);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }

}
