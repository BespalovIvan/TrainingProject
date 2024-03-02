package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.repository.OrderRepo;
import com.example.trainingProject.repository.ProductRepo;
import com.example.trainingProject.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    private final ProductRepo productRepo;

    public OrderServiceImpl(OrderRepo orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }



    @Override
    public void changeStatusOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        orderOptional.ifPresent(order -> orderRepo.changeStatusOrder(order.getId()));
    }
}
