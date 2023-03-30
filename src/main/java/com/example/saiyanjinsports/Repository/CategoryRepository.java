package com.example.saiyanjinsports.Repository;

import com.example.saiyanjinsports.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category , Long> {
    Optional<Category> findByName(String name);
}
