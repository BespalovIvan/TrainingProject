package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.repository.impl.OrderRepoImpl;
import com.example.trainingProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepoImpl orderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepoImpl orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<Order> findBetween(Long with, Long by) {
        return orderRepo.findBetween(with, by);
    }
}
