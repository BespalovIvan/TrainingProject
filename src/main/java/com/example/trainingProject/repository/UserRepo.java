package com.example.trainingProject.repository;

import com.example.trainingProject.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepo {
    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    Long createUser(String name, String email, String password, LocalDateTime createDate, String role);

}
