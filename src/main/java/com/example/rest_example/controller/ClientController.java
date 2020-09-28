package com.example.rest_example.controller;

import com.example.rest_example.model.Client;
import com.example.rest_example.repository.ClientRepository;
import com.example.rest_example.service.ClientService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PostMapping(value = "/saveClients", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveClients(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            clientService.saveClient(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/allClients", produces = "application/json")
    public CompletableFuture<ResponseEntity> findAllClient() {
        return clientService.findAllClients().thenApply(ResponseEntity::ok);
    }

    @GetMapping
    public Page<Client> findAll(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @GetMapping("{id}")
    public Client getOne(@PathVariable("id") Client client) {
        return client;
    }

    @PutMapping("{id}")
    public Client updateClient(@PathVariable(value = "id") Integer Id,
                               @RequestBody Client bookDetails) throws NotFoundException {

        Client client = clientRepository.findById(Id)
                .orElseThrow(() -> new NotFoundException("Client not found!"));

        client.setName(bookDetails.getName());
        client.setEmail(bookDetails.getEmail());
        client.setPhone(bookDetails.getPhone());

        Client updatedClient = clientRepository.save(client);
        return updatedClient;

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Client client) {
        clientRepository.delete(client);
    }

}





