package com.practice.reactiveprogramming.controller;

import com.practice.reactiveprogramming.model.Product;
import com.practice.reactiveprogramming.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
    Controller class
    URL -> http://localhost:8080/products/**
*/
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public Flux<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<Product>> saveProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }

    @PutMapping
    public Mono<Product> updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProductById(@PathVariable int id){
        return service.deleteProductById(id);
    }
}
