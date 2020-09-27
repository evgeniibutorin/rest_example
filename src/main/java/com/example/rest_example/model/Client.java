package com.example.rest_example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String phone;
}
