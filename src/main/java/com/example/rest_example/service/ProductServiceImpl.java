package com.example.rest_example.service;

import com.example.rest_example.model.Product;
import com.example.rest_example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(Product product) { productRepository.save(product);}

    @Override
    public List<Product> readAll() {
        return productRepository.findAll();
    }

    @Override
    public Product read(int id) {
        return productRepository.getOne(id);
    }

    @Override
    public boolean update(Product product, int id) {

        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
            return true;
        }

        return false;
    }



    @Override
    public boolean delete(int id) {
    if(productRepository.existsById(id)) {
    productRepository.deleteById(id);
    return true;
    }

    return false;
    }
}
