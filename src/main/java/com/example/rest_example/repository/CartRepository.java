package com.example.rest_example.repository;

import com.example.rest_example.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository <Cart, Integer> {
}
