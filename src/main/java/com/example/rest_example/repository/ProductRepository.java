package com.example.rest_example.repository;

import com.example.rest_example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByClientId(Integer clientID);
}
