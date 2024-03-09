package com.example.trainingProject.service;

import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.entity.OrderStatus;
import com.example.trainingProject.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    List<OrderProduct> findProductByOrderId(Long orderId);

    void addProductToOrder(Long userId, Long productId);
    void deleteProductFromOrder(Long orderId,Long productId);
}
