package com.example.trainingProject.service.impl;

import com.example.trainingProject.dto.OrderDto;
import com.example.trainingProject.entity.Order;
import com.example.trainingProject.repository.OrderRepo;
import com.example.trainingProject.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;


    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;

    }

    @Override
    public List<OrderDto> findAllByUserId(Long userId) {
        List<Order> orders = orderRepo.findAll(userId);
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            orderDtoList.add(new OrderDto(order.getId(), order.getTotalCost(), order.getOrderCreationDate(), order.getOrderUpdateDate(),
                    order.getStatus()));
        }
        return orderDtoList;
    }


    @Override
    public void changeStatusOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        orderOptional.ifPresent(order -> orderRepo.changeStatusOrder(order.getId()));
    }

    @Override
    public OrderDto findNewOrderByUserId(Long userId) {
        Optional<Order> orderOptional = orderRepo.findNewOrderByUserId(userId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            return new OrderDto(order.getId(), order.getTotalCost(), order.getOrderCreationDate(),
                    order.getOrderUpdateDate(), order.getStatus());
        } else {
            throw new RuntimeException("New order not found");
        }
    }

    @Override
    public OrderDto findById(Long id) {
        Optional<Order> orderOptional = orderRepo.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            return new OrderDto(order.getId(), order.getTotalCost(), order.getOrderCreationDate(),
                    order.getOrderUpdateDate(), order.getStatus());
        } else {
            throw new RuntimeException("Order not found");
        }

    }
}
