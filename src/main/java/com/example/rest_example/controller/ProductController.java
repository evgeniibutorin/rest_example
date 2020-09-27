package com.example.rest_example.controller;

import com.example.rest_example.model.Product;
import com.example.rest_example.repository.ClientRepository;
import com.example.rest_example.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/clients/{clientId}/products")
    public Product addProduct(@PathVariable Integer clientId,
                              @RequestBody Product product) throws NotFoundException {
        return clientRepository.findById(clientId)
                .map(client -> {
                    product.setClient(client);
                    return productRepository.save(product);
                }).orElseThrow(() -> new NotFoundException("Student not found!"));
    }

    //good
    @GetMapping
    public Page<Product> list(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    //good
    @GetMapping(value = "{id}")
    public Product getOne(@PathVariable("id") Product product) {
        return product;
    }

    //good
    @GetMapping("/clients/{clientId}/products")
    public List<Product> getContactByClientId(@PathVariable Integer clientId) throws NotFoundException {

        if (!clientRepository.existsById(clientId)) {
            throw new NotFoundException("Student not found!");
        }

        return productRepository.findByClientId(clientId);
    }

    //good
    @PutMapping("/clients/{clientId}/product/{productId}")
    public Product updateProduct(@PathVariable Integer clientId,
                                 @PathVariable Integer productId,
                                 @RequestBody Product productUpdated) throws NotFoundException {

        if (!clientRepository.existsById(clientId)) {
            throw new NotFoundException("Student not found!");
        }
        return productRepository.findById(productId)
                .map(product -> {
                    product.setName(productUpdated.getName());
                    product.setDescription(productUpdated.getDescription());
                    product.setPrice(productUpdated.getPrice());
                    return productRepository.save(product);
                }).orElseThrow(() -> new NotFoundException("Assignment not found!"));
    }

    //good
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Product product) {
        productRepository.delete(product);
    }

    //good
    @DeleteMapping("/clients/{clientId}/product/{productId}")
    public String deleteAssignment(@PathVariable Integer clientId,
                                   @PathVariable Integer productId) throws NotFoundException {

        if (!clientRepository.existsById(clientId)) {
            throw new NotFoundException("Not found!");
        }
        return productRepository.findById(productId)
                .map(product -> {
                    productRepository.delete(product);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }


}



