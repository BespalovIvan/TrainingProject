package com.example.trainingProject.service;

import com.example.trainingProject.dto.OrderDto;
import com.example.trainingProject.entity.Order;

import java.util.List;

public interface OrderService {

    OrderDto findNewOrderByUserId(Long userId);
    List<OrderDto> findAllByUserId(Long userid);

    void changeStatusOrder(Long userId);

    OrderDto findById(Long id);

}
