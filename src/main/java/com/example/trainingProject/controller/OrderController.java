package com.example.trainingProject.controller;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final Long idUser = 11L;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String findAll(Model model) {
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orders", orderList);
        return "orders";
    }

    @PostMapping("order-create")
    public String createOrder(@RequestParam("id") Long productId) {
        orderService.createOrder(idUser, productId);
        return "redirect:/products";
    }
}
