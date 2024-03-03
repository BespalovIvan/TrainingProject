package com.example.trainingProject.controller;

import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.entity.Product;
import com.example.trainingProject.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final Long idUser = 11L;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String findAll(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products-order")
    public String findProductByOrder(@RequestParam("id") Long orderId, Model model) {
        List<OrderProduct> products = productService.findProductByOrderId(orderId);
        model.addAttribute("productsOrder", products);
        return "productsOrder";
    }

    @PostMapping("add-product")
    public String addProductToOrder(@RequestParam("id") Long productId) {
        productService.addProductToOrder(idUser, productId);
        return "redirect:/products";
    }

    @PostMapping("delete-product")
    public String deleteProductFromOrder(@RequestParam("order_id") Long orderId,@RequestParam("product_id") Long productId) {
        productService.deleteProductFromOrder(orderId, productId);
        return "redirect:/productsOrder";
    }
}
