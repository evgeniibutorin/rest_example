package com.example.rest_example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity //указывает, что данный бин (класс) является сущностью
@Table(name = "clients") //указывает на имя таблицы, которая будет отображаться в этой сущности
@Getter
@Setter
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


    @OneToOne(cascade = CascadeType.ALL) // CascadeType.ALL указывает, что действие которое мы применфем к основному объекту будет применено и к ассоциируемому сохранили тут сохранилось и там то же с удалением
    @JoinColumn(name = "Cart_id")
    private Cart cart;

   }
