package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.repository.OrderRepo;
import com.example.trainingProject.repository.ProductRepo;
import com.example.trainingProject.repository.UserRepo;
import com.example.trainingProject.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo, UserRepo userRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
    }


    @Override
    public List<Order> findBetween(Long with, Long by) {
        return orderRepo.findBetween(with, by);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepo.findById(id);
    }

    @Override
    public Long createOrder(Long userId, LocalDateTime dateTime, Long productId) {

        return orderRepo.createOrder(userId, dateTime, productId);
    }
}
