package com.example.trainingProject.service;

import com.example.trainingProject.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findBetween(Long with, Long by);

    Optional<User> findById(Long id);
}
