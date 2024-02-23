package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.repository.OrderRepo;
import com.example.trainingProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
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
}
