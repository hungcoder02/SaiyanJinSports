package com.example.saiyanjinsports.Repository;

import com.example.saiyanjinsports.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);


    @Query(value = "SELECT * FROM product", nativeQuery = true)
    Page<Product> findAll(Pageable pageable, @Param("name") String s);

    @Query(value = "SELECT * FROM product WHERE product.gender_id = 1 AND" +
            " (:name = '' or product.pro_name like concat('%', :name, '%')) ",nativeQuery = true)
    Page<Product> findMenProducts(Pageable pageable, @Param("name") String s);

    @Query(value = "SELECT * FROM product WHERE product.gender_id = 2 AND" +
            " (:name = '' or product.pro_name like concat('%', :name, '%'))",nativeQuery = true)
    Page<Product> finWomenProducts(Pageable pageable,@Param("name") String s);


}
