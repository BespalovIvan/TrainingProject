package com.example.trainingProject.controller;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String findOrderIdBetween(@RequestParam("with") Long with, @RequestParam("by") Long by, Model model){
        List<Order> orders = orderService.findBetween(with,by);
        model.addAttribute("orders",orders);
        return "orders";
    }
}
