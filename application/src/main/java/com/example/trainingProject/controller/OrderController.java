package com.example.trainingProject.controller;

import com.example.trainingProject.config.MyUserDetails;
import com.example.trainingProject.dto.OrderDto;
import com.example.trainingProject.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String findAll(@AuthenticationPrincipal MyUserDetails user, Model model) {
        List<OrderDto> orderDtoList = orderService.findAllByUserId(user.getUserId());
        model.addAttribute("orders", orderDtoList);
        return "orders";
    }

    @GetMapping("/new-orders")
    public String findNewOrders(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("order", orderService.findNewOrderByUserId(user.getUserId()));
        return "order";
    }

    @PostMapping("/status-update/{order_id}")
    public String changeStatus(@PathVariable("order_id") Long orderId) {
        orderService.changeStatusOrder(orderId);
        return "redirect:/products-order/{order_id}";
    }
}
