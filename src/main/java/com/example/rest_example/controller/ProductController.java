package com.example.rest_example.controller;

import com.example.rest_example.model.Product;
import com.example.rest_example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductRepository productRepository;


    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> list() {
        return productRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Product getOne(@PathVariable("id") Product product) {
        return product;
    }

    @PutMapping(value = "{id}")
    public Product update(@PathVariable(name = "id") Product productDB, @RequestBody Product product) {
        return productRepository.save(productDB);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Product product) {
        productRepository.delete(product);
    }

}



