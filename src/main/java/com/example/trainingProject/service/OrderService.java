package com.example.trainingProject.service;

import com.example.trainingProject.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findBetween(Long with, Long by);

    Optional<Order> findById(Long id);
}
