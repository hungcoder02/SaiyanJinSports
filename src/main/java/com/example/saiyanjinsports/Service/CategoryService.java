package com.example.saiyanjinsports.Service;

import com.example.saiyanjinsports.Entities.Category;
import com.example.saiyanjinsports.Model.CategoryDTO;
import com.example.saiyanjinsports.Model.Response;
import com.example.saiyanjinsports.Payload.request.Category.CreateCategory;
import com.example.saiyanjinsports.Payload.request.Category.DeleteCategory;
import com.example.saiyanjinsports.Payload.request.Category.UpdateCategory;
import com.example.saiyanjinsports.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Response getAll(){
        Response response = new Response();
        try {
            List<Category> categories =categoryRepository.findAll();
            List<CategoryDTO> categoryDTOS = new ArrayList<>();
            for (Category c : categories){
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(c.getId());
                categoryDTO.setName(c.getName());
            }
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(categoryDTOS);
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
            Category category = categoryRepository.findById(id).orElse(null);
            if (category == null){
                response.setStatus(400);
                response.setMessage("Cannot Found");
                return response;
            }
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            response.setStatus(200);
            response.setMessage("OK");
            response.setData(categoryDTO);
            return response;
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
    public Response create(CreateCategory createCategory){
        Response response = new Response();
        try {
            Category category = categoryRepository.findByName(createCategory.getName()).orElse(null);
            if (category == null){
                category = new Category();
                category.setName(createCategory.getName());
                categoryRepository.save(category);
                response.setStatus(200);
                response.setMessage("Ok");
                return response;
            }else {
                response.setStatus(400);
                response.setMessage("Already Exists");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("Internal Sever Errors");
            return response;
        }
    }
    public Response update(UpdateCategory dto){
        Response response = new Response();
        try {
            Category category = categoryRepository.findById(dto.getId()).orElse(null);
            if (category != null){
                Category check = categoryRepository.findByName(dto.getName()).orElse(null);
                if (check == null){
                    category.setName(dto.getName());
                    categoryRepository.save(category);
                    response.setStatus(200);
                    response.setMessage("OK");
                    return response;
                }else {
                    response.setStatus(400);
                    response.setMessage("Product Already Exist");
                    return response;
                }
            }else {
                response.setStatus(400);
                response.setMessage("Product Already Exist");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("OMG!");
            response.getError().add(e.getMessage());
            return response;
        }
    }
    public Response delete(DeleteCategory dto){
        Response response = new Response();
        try {
            Category category = categoryRepository.findById(dto.getId()).orElse(null);
            if (category != null){
                category.setId(dto.getId());
                categoryRepository.delete(category);
                response.setStatus(200);
                response.setMessage("Deleted");
                return response;
            }else {
                response.setStatus(400);
                response.setMessage("Does not exist");
                return response;
            }
        }catch (Exception e){
            response.setStatus(500);
            response.setMessage("OMG!");
            return response;
        }
    }
}
