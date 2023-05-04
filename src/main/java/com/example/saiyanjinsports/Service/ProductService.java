package com.example.saiyanjinsports.Service;

import com.example.saiyanjinsports.Entities.Product;
import com.example.saiyanjinsports.Model.ProductDTO;
import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.Product.CreateProduct;
import com.example.saiyanjinsports.Payload.request.Product.DeleteProduct;
import com.example.saiyanjinsports.Payload.request.Product.UpdateProduct;
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
                productDTO.setName(p.getName());
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
    public Response getOne(long id){
        Response response = new Response();
        try {
            Product product = productRepository.findById(id).orElse(null);
            if(product == null){
                response.setStatus(404);
                response.setMessage("Cannot found");
                return response;
            }
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setInformation(product.getInformation());
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(productDTO);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }

    public Response create(CreateProduct createProduct){
        Response response = new Response();
        try {
            Product product = productRepository.findByName(createProduct.getName()).orElse(null);
            if(product == null){
                product = new Product();
                product.setName(createProduct.getName());
                product.setPrice(createProduct.getPrice());
                product.setDescription(createProduct.getDescription());
                product.setInformation(createProduct.getInformation());
                productRepository.save(product);
                response.setStatus(200);
                response.setMessage("Created");
                return response;
            } else {
                response.setStatus(400);
                response.setMessage("Exist");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
    public Response update(UpdateProduct dto){
        Response response = new Response();
        try {
            Product product = productRepository.findById(dto.getId()).orElse(null);
            if (product != null){
                Product check = productRepository.findByName(dto.getName()).orElse(null);
                if(check == null){
                    product.setName(dto.getName());
                    product.setPrice(dto.getPrice());
                    product.setDescription(dto.getDescription());
                    product.setInformation(dto.getInformation());
                    productRepository.save(product);
                    response.setStatus(200);
                    response.setMessage("OK");
                    return response;
                } else {
                    response.setStatus(400);
                    response.setMessage("Product Already Exist");
                    return response;
                }
            } else {
                response.setStatus(400);
                response.setMessage("Does not exist");
                return response;
            }
        } catch (Exception e){
            response.setStatus(500);
            response.setMessage("OMG!");
            response.getError().add(e.getMessage());
            return response;
        }
    }
    public Response delete(DeleteProduct dto){
        Response response = new Response();
        try {
            Product product = productRepository.findById(dto.getId()).orElse(null);
            if(product != null){
                product.setId(dto.getId());
                productRepository.delete(product);
                response.setStatus(200);
                response.setMessage("Deleted");
                return response;
            }else {
                response.setStatus(400);
                response.setMessage("Does not exist");
                return response;
            }
        } catch (Exception e){
            response.setStatus(500);
            response.setMessage("OMG!");
            return response;
        }
    }
}
