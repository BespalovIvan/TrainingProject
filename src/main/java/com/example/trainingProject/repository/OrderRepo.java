package com.example.trainingProject.repository;

import com.example.trainingProject.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepo {

     List<Order> findAll(Long user_id);
     Optional<Order> findNewOrderByUserId(Long id);
     Order createOrder(Long userId);
     Optional<Order> findById(Long id);
     void changeStatusOrder(Long orderId);
     void plusTotalCost(Long orderId, BigDecimal sum);
     void minusTotalCost(Long orderId, BigDecimal sum);
     void changeUpdateDate (LocalDateTime date,Long orderId);
     void deleteOrder(Long orderId);
}
