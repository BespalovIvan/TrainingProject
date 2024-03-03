package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.entity.Product;
import com.example.trainingProject.repository.OrderRepo;
import com.example.trainingProject.repository.ProductRepo;
import com.example.trainingProject.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ProductServiceImpl(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public List<OrderProduct> findProductByOrderId(Long orderId) {
        List<OrderProduct> products = new ArrayList<>();
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            products = productRepo.findProductByOrderId(order.getId());
        }
        return products;
    }

    @Override
    public void addProductToOrder(Long userId, Long productId) {
        BigDecimal totalCost = BigDecimal.valueOf(0);
        Order order = orderRepo.findNewOrderByUserId(userId).orElseGet(() -> orderRepo.createOrder(userId));
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            totalCost = totalCost.add(product.getPrice());
            orderRepo.plusTotalCost(order.getId(), totalCost);
        }
        productRepo.addProductToOrder(productId, order.getId());
    }

    @Override
    public void deleteProductFromOrder(Long orderId, Long productId) {
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            if (order.getStatus().toString().equals("NEW")&&optionalProduct.isPresent()) {
                productRepo.deleteProductFromOrder(orderId, productId);
                Product product = optionalProduct.get();
                BigDecimal totalCost = order.getTotalCost().subtract(product.getPrice());
                orderRepo.minusTotalCost(order.getId(),totalCost);
            }
        }
    }
}
