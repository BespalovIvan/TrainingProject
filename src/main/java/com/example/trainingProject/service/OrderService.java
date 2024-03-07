package com.example.trainingProject.service;

import com.example.trainingProject.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order findNewOrderByUserId(Long userId);
    List<Order> findAll();

    void changeStatusOrder(Long userId);

    Order findById(Long id);

}
