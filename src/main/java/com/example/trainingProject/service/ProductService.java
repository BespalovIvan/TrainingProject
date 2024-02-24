package com.example.trainingProject.service;

import com.example.trainingProject.entity.Product;

import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);
}
