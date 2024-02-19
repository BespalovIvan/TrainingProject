package com.example.trainingProject.repository;

import com.example.trainingProject.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepo {
     List<Order> findBetween(Long with,Long by);
     Order findById(Long id);
     List<Order> findByUserId(Long id);
     Long createOrder(Long userId, LocalDateTime dateTime, Long productId, Integer countProducts);
     void deleteOrder(Integer id);
}
