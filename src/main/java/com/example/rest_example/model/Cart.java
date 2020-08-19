package com.example.rest_example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer id_client;
    private Integer id_product;


}
