package com.example.trainingProject.controller;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new-orders")
    public String findNewOrders(Model model) {
        model.addAttribute("order", orderService.findNewOrderByUserId(idUser));
        return "order";
    }

    @PostMapping("/status-update/{order_id}")
    public String changeStatus(@PathVariable("order_id") Long orderId) {
        orderService.changeStatusOrder(orderId);
        return "redirect:/products-order/{order_id}";
    }
    @ExceptionHandler(RuntimeException.class)
    public String handleException() {
        return "errorNewOrder";
    }
}
