package com.example.rest_example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private String price;
}
