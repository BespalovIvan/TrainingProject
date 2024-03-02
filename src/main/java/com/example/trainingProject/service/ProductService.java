package com.example.trainingProject.service;

import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

//    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findProductByOrderId(Long orderId);
    OrderProduct addProductToOrder(Long userId,Long productId);
}
