package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.Product;
import com.example.trainingProject.repository.ProductRepo;
import com.example.trainingProject.service.ProductService;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }
}
