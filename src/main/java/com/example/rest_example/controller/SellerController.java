package com.example.rest_example.controller;

import com.example.rest_example.model.Seller;
import com.example.rest_example.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping(value = "/seller", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            sellerService.saveSeller(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/seller", produces = "application/json")
    public CompletableFuture<ResponseEntity> findAllSeller() {
        return  sellerService.findAllSellers().thenApply(ResponseEntity::ok);
    }


    @GetMapping(value = "/getUsersByThread", produces = "application/json")
    public  ResponseEntity getUsers(){
        CompletableFuture<List<Seller>> seller1=sellerService.findAllSellers();
        CompletableFuture<List<Seller>> seller2=sellerService.findAllSellers();
        CompletableFuture<List<Seller>> seller3=sellerService.findAllSellers();
        CompletableFuture.allOf(seller1,seller2,seller3).join();
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
