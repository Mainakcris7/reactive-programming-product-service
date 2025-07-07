package com.practice.reactiveprogramming.repository;

import com.practice.reactiveprogramming.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
}
