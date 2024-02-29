package com.example.trainingProject.service;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.entity.Product;

import java.util.List;

public interface OrderService {

    //    Optional<Order> findById(Long id);
    List<Order> findAll();
    void changeStatusOrder(Long userId);

    OrderProduct createOrder(Long userId, Long productId);
}
