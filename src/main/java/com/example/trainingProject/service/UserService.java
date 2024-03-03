package com.example.trainingProject.service;

import com.example.trainingProject.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    Long createUser(String name, String email);

    void deleteUserById(Long id);

    Long updateUserById(Long id, String name, String email);
}
