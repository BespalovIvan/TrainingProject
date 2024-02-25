package com.example.trainingProject.repository;

import com.example.trainingProject.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface ProductRepo {
    List<Product> findAll();
    Optional<Product> findById(Long productId);
//    List<Product> findBetween(Long with, Long by);
//    Optional<Product> findById(Long id);
    Product addProductToOrder(Long productId, Long orderId);
    Long updateProduct(Long id, String name, BigDecimal price);
    void deleteProductById(Long id);
}
