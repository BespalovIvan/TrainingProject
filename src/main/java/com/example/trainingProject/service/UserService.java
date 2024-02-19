package com.example.trainingProject.service;

import com.example.trainingProject.entity.User;

import java.util.List;

public interface UserService  {
    public List<User> findBetween(Long with, Long by);
}
