package com.example.saiyanjinsports.Repository;

import com.example.saiyanjinsports.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User>
}
