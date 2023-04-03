package com.example.saiyanjinsports.Repository;

import com.example.saiyanjinsports.Entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
    Optional<Images> findByName(String name);
}
