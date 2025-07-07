package com.practice.reactiveprogramming.service;

import com.practice.reactiveprogramming.exception.ProductException;
import com.practice.reactiveprogramming.model.Product;
import com.practice.reactiveprogramming.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repo;

    /* GET methods */
    public Flux<Product> getAllProducts(){
        return repo.findAll()
                .switchIfEmpty(
                        Flux.error(new ProductException(HttpStatus.BAD_REQUEST, "No products found"))
                );
    }

    public Mono<Product> getProductById(int id){
        return repo.findById(id)
                .switchIfEmpty(
                        Mono.error(new ProductException(HttpStatus.BAD_REQUEST, "No products found with id: " + id))
                );
    }

    /* POST methods */
    public Mono<ResponseEntity<Product>> saveProduct(Product product){
        product.setId(0);
        return repo.save(product)
                .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p))
                .switchIfEmpty(
                        Mono.error(new ProductException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save the product"))
                );
    }

    /* PUT methods */
    public Mono<Product> updateProduct(Product product){
        return repo.findById(product.getId())
                .doOnNext(p -> {
                    p.setId(product.getId());
                    p.setName(product.getName());
                    p.setQuantity(product.getQuantity());
                    p.setPrice(product.getPrice());
                })
                .flatMap(repo::save)
                .switchIfEmpty(Mono.error(
                        new ProductException(HttpStatus.BAD_REQUEST, "No products found with id:" + product.getId())
                ));
    }

    /* DELETE methods */
    public Mono<ResponseEntity<Void>> deleteProductById(int id){
        return repo.existsById(id)
                .flatMap(exists -> {
                    if(exists){
                        return repo.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
                    }else{
                        return Mono.error(new ProductException(HttpStatus.BAD_REQUEST, "No products found with id:" + id));
                    }
                });
    }
}
