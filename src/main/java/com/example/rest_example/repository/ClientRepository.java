package com.example.rest_example.repository;

import com.example.rest_example.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer>  {
}


    /**JpaRepository выходит PagingAndSortingRepository, который, в свою очередь, расширяет CrudRepository.
     *  главным образом обеспечивает функции CRUD.
     *  предоставляет методы для разбиения на страницы и сортировки записей.
     *  предоставляет некоторые связанные с JPA методы, такие как очистка контекста сохраняемости и удаление записей в пакете.
     */
