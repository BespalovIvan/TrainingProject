package com.example.trainingProject.repository;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderStatus;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepo {

     List<Order> findAll();
     Optional<Order> findNewOrderByUserId(Long id);
     Order createOrder(Long userId);
     Optional<Order> findById(Long id);
     void changeStatusOrder(Long orderId);
     void deleteOrder(Integer id);
}
