package com.example.trainingProject.service;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderProducts;

import java.util.List;

public interface OrderService {

    //    Optional<Order> findById(Long id);
    List<Order> findAll();

    OrderProducts createOrder(Long userId, Long productId);
}
