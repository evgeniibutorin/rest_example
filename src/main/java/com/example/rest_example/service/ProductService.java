package com.example.rest_example.service;

import com.example.rest_example.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Создает новый продукт
     * @param product - продукт для создания
     */
    void create(Product product);

    /**
     * Возвращает список всех имеющихся продуктов
     * @return список клиентов
     */
    List<Product> readAll();

    /**
     * Возвращает продукт по его ID
     * @param id - ID продукта
     * @return - объект продукта с заданным ID
     */
    Product read(int id);

    /**
     * Обновляет продукт с заданным ID,
     * в соответствии с переданным товаром
     * @param product - продукт в соответсвии с которым нужно обновить данные
     * @param id - id продукта которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Product product, int id);

    /**
     * Удаляет продукт с заданным ID
     * @param id - id продукта, которого нужно удалить
     * @return - true если продукт был удален, иначе false
     */
    boolean delete(int id);
}



