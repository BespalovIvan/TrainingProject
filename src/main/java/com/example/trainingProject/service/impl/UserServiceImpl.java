package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.trainingProject.repository.impl.UserRepoImpl;
import com.example.trainingProject.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepoImpl userRepo;


    public UserServiceImpl(UserRepoImpl userRepo) {
        this.userRepo = userRepo;
    }


    public List<User> findBetween(Long with, Long by) {
        return userRepo.findBetween(with, by);
    }
}
