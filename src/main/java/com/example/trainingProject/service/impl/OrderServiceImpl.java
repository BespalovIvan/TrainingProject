package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.repository.OrderRepo;
import com.example.trainingProject.repository.ProductRepo;
import com.example.trainingProject.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public OrderProduct createOrder(Long userId, Long productId) {
        Order order = orderRepo.findNewOrderByUserId(userId).orElseGet(() -> orderRepo.createOrder(userId));
        return new OrderProduct(order, productRepo.addProductToOrder(productId, order.getId()));
    }
}
