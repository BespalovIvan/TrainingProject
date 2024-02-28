package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.Product;
import com.example.trainingProject.repository.OrderRepo;
import com.example.trainingProject.repository.ProductRepo;
import com.example.trainingProject.service.ProductService;
import org.springframework.stereotype.Service;

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
    public List<Product> findProductByOrderId(Long orderId) {
        List<Product> products = new ArrayList<>();
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            products = productRepo.findProductByOrderId(order.getId());
        }
        return products;
    }

    //
//    @Override
//    public Optional<Product> findById(Long id) {
//        return productRepo.findById(id);
//    }
}
