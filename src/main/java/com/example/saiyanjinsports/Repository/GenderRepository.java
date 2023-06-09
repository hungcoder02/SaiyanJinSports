package com.example.saiyanjinsports.Repository;

import com.example.saiyanjinsports.Entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GenderRepository extends JpaRepository<Gender , Long> {
    Optional<Gender> findByName(String name);
}
