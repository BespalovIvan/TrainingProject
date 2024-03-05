package com.example.trainingProject.service;

import com.example.trainingProject.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    //    Optional<Order> findById(Long id);
    List<Order> findAll();

    void changeStatusOrder(Long userId);

    Optional<Order> findById(Long id);

}
