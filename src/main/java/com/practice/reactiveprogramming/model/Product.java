package com.practice.reactiveprogramming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/*
    Model class, that is mapped to a Database table
*/
@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(value = "id")
    private int id;

    @Column(value = "name")
    private String name;

    @Column(value = "quantity")
    private int quantity;

    @Column(value = "price")
    private BigDecimal price;
}
