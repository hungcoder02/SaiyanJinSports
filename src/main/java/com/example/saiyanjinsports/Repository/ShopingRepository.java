package com.example.saiyanjinsports.Repository;

import com.example.saiyanjinsports.Entities.Shopping;
import com.example.saiyanjinsports.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopingRepository extends JpaRepository<Shopping, Long> {

}
