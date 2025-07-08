package com.practice.reactiveprogramming.controller;

import com.practice.reactiveprogramming.model.Product;
import com.practice.reactiveprogramming.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@WebFluxTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private WebTestClient client;

    @MockitoBean
    private ProductService service;

    @Test
    public void testGetAllProducts(){
        Flux<Product> products = Flux.just(
                new Product(1, "TV", 2, new BigDecimal("20000")),
                new Product(2, "Fridge", 3, new BigDecimal("60000"))
        );

        when(service.getAllProducts()).thenReturn(products);

        Flux<Product> allProducts = client.get().uri("/products")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Product.class)
                .getResponseBody();

        StepVerifier.create(allProducts)
                .expectSubscription()
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void testGetProductById(){
        Mono<Product> product = Mono.just(
                new Product(1, "TV", 2, new BigDecimal("20000"))
        );

        when(service.getProductById(anyInt())).thenReturn(product);

        Mono<Product> theProduct = client.get().uri("/products/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Product.class)
                .getResponseBody()
                .next();

        StepVerifier.create(theProduct)
                .expectSubscription()
                .expectNextMatches(p -> p.getName().equals("TV"))
                .verifyComplete();
    }

    @Test
    public void testSaveProduct(){
        Product product = new Product(1, "Mobile", 2, new BigDecimal("10000"));

        Mono<ResponseEntity<Product>> response = Mono.just(
                ResponseEntity.status(HttpStatus.CREATED).body(product)
        );

        when(service.saveProduct(product)).thenReturn(response);

        Mono<Product> savedProduct = client.post().uri("/products")
                .body(Mono.just(product), Product.class)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(Product.class)
                .getResponseBody()
                .next();

        StepVerifier.create(savedProduct)
                .expectSubscription()
                .expectNextMatches(p -> p.getId() == 1)
                .verifyComplete();
    }

    @Test
    public void testUpdateProduct(){
        Product product = new Product(1, "Mobile", 2, new BigDecimal("10000"));

        Mono<Product> productMono = Mono.just(
                new Product(1, "Mobile", 2, new BigDecimal("15000"))
        );

        when(service.updateProduct(product)).thenReturn(productMono);

        Flux<Product> updatedProduct = client.put().uri("/products")
                .body(Mono.just(product), Product.class)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Product.class)
                .getResponseBody();

        StepVerifier.create(updatedProduct)
                .expectSubscription()
                .expectNextMatches(p -> p.getPrice().equals(new BigDecimal("15000")))
                .verifyComplete();
    }

    @Test
    public void testDeleteProductById(){
        when(service.deleteProductById(anyInt())).thenReturn(
                Mono.just(ResponseEntity.noContent().build())
        );

        client.delete().uri("/products/{id}", 2)
                .exchange()
                .expectStatus().isNoContent();
    }
}
