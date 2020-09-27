package com.example.rest_example.service;

import com.example.rest_example.model.Client;
import com.example.rest_example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Async
    public CompletableFuture<List<Client>> saveClient(MultipartFile file) throws Exception {
        List<Client> clients = parseFile(file);
        clients = clientRepository.saveAll(clients);
        return CompletableFuture.completedFuture(clients);
    }

    @Async
    public CompletableFuture<List<Client>> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return CompletableFuture.completedFuture(clients);
    }

    private List<Client> parseFile(final MultipartFile file) throws Exception {
        final List<Client> clients = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Client client = new Client();
                    client.setName(data[0]);
                    client.setEmail(data[1]);
                    client.setPhone(data[2]);
                    clients.add(client);
                }
                return clients;
            }
        } catch (final IOException e) {
            throw new Exception("Failed to parse file {}", e);
        }
    }

}


