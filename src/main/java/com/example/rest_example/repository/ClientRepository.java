package com.example.rest_example.repository;

import com.example.rest_example.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer>  {

   //@Query("select s from Client s where name like %?1%")
   //Page<Client> findByName(String name, Pageable pageable);
    

}


    /**JpaRepository выходит PagingAndSortingRepository, который, в свою очередь, расширяет CrudRepository.
     *  главным образом обеспечивает функции CRUD.
     *  предоставляет методы для разбиения на страницы и сортировки записей.
     *  предоставляет некоторые связанные с JPA методы, такие как очистка контекста сохраняемости и удаление записей в пакете.
     */
