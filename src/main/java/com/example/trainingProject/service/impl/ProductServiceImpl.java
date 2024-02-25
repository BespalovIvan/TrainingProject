package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.Product;
import com.example.trainingProject.repository.ProductRepo;
import com.example.trainingProject.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }
//
//    @Override
//    public Optional<Product> findById(Long id) {
//        return productRepo.findById(id);
//    }
}
