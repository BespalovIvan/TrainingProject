package com.example.trainingProject.repository;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepo {
//    List<Order> findBetween(Long with,Long by);
//     Optional<Order> findById(Long id);
     List<Order> findByUserId(Long id);
     Long createOrder(Long userId, Integer totalCost, LocalDateTime orderCreationDate,
                      LocalDateTime orderUpdateDate, OrderStatus status,Long productId);
     void deleteOrder(Integer id);
}
