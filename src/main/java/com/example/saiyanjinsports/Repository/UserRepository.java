package com.example.saiyanjinsports.Repository;

import com.example.saiyanjinsports.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User>
}
