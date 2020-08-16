package com.example.rest_example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)//установили новую аннотацию @SequenceGenerator для создания генератора последовательности, назначили ему имя clientsIdSeq, указали что это генератор для последовательности clients_id_seq, и добавили атрибут allocationSize = 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq") //указывает, что данное свойство будет создаваться согласно указанной стратегии можо поставить AUTO
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

}
