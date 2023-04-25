package com.example.saiyanjinsports.Payload.request.Product;

import com.example.saiyanjinsports.Entities.Product;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ProductView {
    private Page<Product> products;

    private int total;

}
