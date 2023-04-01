package com.example.saiyanjinsports.Repository;

import com.example.saiyanjinsports.Entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender , Long> {
    Optional<Gender> findByName(String name);
}
