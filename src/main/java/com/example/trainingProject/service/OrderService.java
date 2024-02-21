package com.example.trainingProject.service;

import com.example.trainingProject.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findBetween(Long with, Long by);
}
