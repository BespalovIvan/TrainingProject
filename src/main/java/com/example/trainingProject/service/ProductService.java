package com.example.trainingProject.service;

import com.example.trainingProject.dto.OrderProductDto;
import com.example.trainingProject.dto.ProductDto;
import com.example.trainingProject.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    List<OrderProductDto> findProductByOrderId(Long orderId);

    void addProductToOrder(Long userId, Long productId);
    void deleteProductFromOrder(Long orderId,Long productId);
}
