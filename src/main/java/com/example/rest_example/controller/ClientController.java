package com.example.rest_example.controller;

import com.example.rest_example.model.Client;
import com.example.rest_example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RestController — говорит спрингу, что данный класс является REST контроллером. Т.е. в данном классе будет реализована логика обработки клиентских запросов
@RequestMapping("clients")
public class ClientController {

    private final ClientRepository clientRepository;


    /* @Autowired — говорит спрингу, что в этом месте необходимо внедрить зависимость.
    */
      @Autowired
      public ClientController(ClientRepository clientRepository) {
          this.clientRepository = clientRepository;
     }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping
    public List<Client> list(){
         return clientRepository.findAll();
    }

    @GetMapping("{id}")
    public Client getOne(@PathVariable("id") Client client) {
        return client;
    }

    @PutMapping("{id}")
    public Client client(@PathVariable(name = "id") Client clientDB, @RequestBody Client client) {
        return clientRepository.save(clientDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Client client) {
        clientRepository.delete(client);
    }


}
