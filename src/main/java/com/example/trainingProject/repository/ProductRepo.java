package com.example.trainingProject.repository;

import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.entity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepo {
    List<Product> findAll();
    List<OrderProduct> findProductByOrderId(Long orderId);
    Optional<Product> findById(Long productId);
    Product addProductToOrder(Long productId, Long orderId);
    void deleteProductFromOrder(Long orderId,Long productId);
}
