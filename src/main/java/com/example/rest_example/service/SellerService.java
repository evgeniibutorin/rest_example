package com.example.rest_example.service;

import com.example.rest_example.model.Seller;
import com.example.rest_example.repository.SellerRepository;
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
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Async //аннотирование метода bean-компонента с помощью @Async заставит его выполняться в отдельном потоке, то есть вызывающий не будет ждать завершения вызванного метода.
    public CompletableFuture<List<Seller>> saveSeller(MultipartFile file) throws Exception { //класс CompletableFuture — средство для передачи информации между параллельными потоками исполнения. интерфейс MultipartFile Представление загруженного файла, полученного в составном запросе.
        List<Seller> sellers = parseFile(file);
        sellers = sellerRepository.saveAll(sellers);
        return CompletableFuture.completedFuture(sellers); //completedFuture(sellers) Возвращает новый объект CompletableFuture, который уже завершен с заданным значением.
    }

    @Async
    public CompletableFuture<List<Seller>> findAllSellers(){
        List<Seller> sellers=sellerRepository.findAll();
        return CompletableFuture.completedFuture(sellers);
    }

    private List<Seller> parseFile(final MultipartFile file) throws Exception {
        final List<Seller> sellers = new ArrayList<>();
        try  {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) { // BufferedReader класс считывает текст из символьного потока ввода, буферизируя прочитанные символы. InputStreamReader получает данные из потока, считывает байты и декодирует их в символы
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Seller seller = new Seller();
                    seller.setName(data[0]);
                    seller.setEmail(data[1]);
                    seller.setPhone(data[2]);
                    sellers.add(seller);
                }
                return sellers;
            }
        } catch (final IOException e) {
            throw new Exception("Failed to parse file {}", e);
        }
    }


}
