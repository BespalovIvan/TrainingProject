package com.example.trainingProject.controller;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.Product;
import com.example.trainingProject.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @GetMapping("/orders")
//    public String findOrderIdBetween(@RequestParam("with") Long with, @RequestParam("by") Long by, Model model) {
//        List<Order> orders = orderService.findBetween(with, by);
//        model.addAttribute("orders", orders);
//        return "orders";
//    }

//    @GetMapping("/order")
//    public String findById(@RequestParam("id") Long id, Model model) {
//        Optional<Order> orderOptional = orderService.findById(id);
//        if (orderOptional.isPresent()) {
//            model.addAttribute("order", orderOptional.get());
//        } else {
//            model.addAttribute("order", new Order());
//        }
//        return "order";
//    }

    @GetMapping("/order-create/{id}")
    public String createOrderForm(Order order, @PathVariable("id") Long productId) {

        return "redirect:/orders";
    }
}
