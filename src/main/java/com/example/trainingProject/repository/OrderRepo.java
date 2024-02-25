package com.example.trainingProject.repository;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepo {

     List<Order> findAll();
     Optional<Order> findNewOrderByUserId(Long id);
     Order createOrder(Long userId);
     void deleteOrder(Integer id);
}
