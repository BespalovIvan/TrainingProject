package com.example.trainingProject.service;

import com.example.trainingProject.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    boolean createUser(User user);

}
