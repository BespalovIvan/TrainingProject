package com.example.trainingProject.service;

import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.entity.Product;

import java.util.List;

public interface ProductService {

    //    Optional<Product> findById(Long id);
    List<Product> findAll();

    List<OrderProduct> findProductByOrderId(Long orderId);

    void addProductToOrder(Long userId, Long productId);
    void deleteProductFromOrder(Long orderId,Long productId);
}
