package com.example.trainingProject.repository;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.dto.OrderProductDto;
import com.example.trainingProject.entity.Product;

import java.io.File;
import java.util.List;
import java.util.Optional;


public interface ProductRepo {
    List<Product> findAll();
    byte[] findImageById(Long productId);
    List<OrderProductDto> findProductByOrderId(Order order);
    Optional<Product> findById(Long productId);
    Product addProductToOrder(Long productId, Long orderId);
    void deleteProductFromOrder(Long orderId,Long productId);
}
