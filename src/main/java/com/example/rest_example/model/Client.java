package com.example.rest_example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity //указывает, что данный бин (класс) является сущностью
@Table(name = "clients") //указывает на имя таблицы, которая будет отображаться в этой сущности
public class Client {

    @Id //id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных таблице
    @Column(name = "id") //указывает на имя колонки, которая отображается в свойство сущности
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)//установили новую аннотацию @SequenceGenerator для создания генератора последовательности, назначили ему имя clientsIdSeq, указали что это генератор для последовательности clients_id_seq, и добавили атрибут allocationSize = 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq") //указывает, что данное свойство будет создаваться согласно указанной стратегии
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
