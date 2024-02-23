package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.User;
import com.example.trainingProject.repository.UserRepo;
import com.example.trainingProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepoSpring) {
        this.userRepo = userRepoSpring;
    }


    public List<User> findBetween(Long with, Long by) {
        return userRepo.findBetween(with, by);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);

    }
}
