package com.example.trainingProject.repository;

import com.example.trainingProject.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo{
    List<User> findBetween(Long with, Long by);
    Optional<User> findById(Long id);
    Long createUser(String name,String email);
    Long updateUserById(Long id, String name,String email);
    void deleteUserById(Long id);
}
