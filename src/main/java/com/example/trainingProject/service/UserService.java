package com.example.trainingProject.service;

import com.example.trainingProject.entity.User;

import java.util.List;

public interface UserService {
    List<User> findBetween(Long with, Long by);

    User findById(Long id);
}
