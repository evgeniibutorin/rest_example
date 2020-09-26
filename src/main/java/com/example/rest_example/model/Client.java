package com.example.rest_example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity //указывает, что данный бин (класс) является сущностью
@Table(name = "clients") //указывает на имя таблицы, которая будет отображаться в этой сущности
@Data //это удобная сокращённая аннотация, которая содержит в себе возможности из @ToString, @EqualsAndHashCode, @Getter / @Setter и @RequiredArgsConstructor.
public class Client {

    @Id //id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных таблице
    @Column(name = "id") //указывает на имя колонки, которая отображается в свойство сущности
    @GeneratedValue(strategy = GenerationType.AUTO) //указывает, что данное свойство будет создаваться согласно указанной стратегии можо поставить AUTO
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL) //параметр cascade говорит JPA, что делать с владеемыми объектами при операциях над владельцем.
    @JoinColumn(name = "cp_fk", referencedColumnName = "id")
    private List<Product> products;

   }
