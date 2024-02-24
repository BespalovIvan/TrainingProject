package com.example.trainingProject.repository;

import com.example.trainingProject.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface ProductRepo {
    List<Product> findBetween(Long with, Long by);
    Optional<Product> findById(Long id);
    Long createProduct (String name, BigDecimal price);
    Long updateProduct(Long id, String name, BigDecimal price);
    void deleteProductById(Long id);
}
