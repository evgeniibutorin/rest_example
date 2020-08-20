package com.example.rest_example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Cart")
@Getter
@Setter
public class Cart {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer client_id;

    @Column
    private Integer product_id;

    @Column
    @OneToOne(mappedBy = "cart",cascade = CascadeType.ALL)//mappedBy = "id" указывается имя которое соответсвуеь полю в Person
    private Client client;

    }
