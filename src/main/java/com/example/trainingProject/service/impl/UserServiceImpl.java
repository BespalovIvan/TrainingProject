package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.User;
import com.example.trainingProject.repository.impl.UserRepoImpl;
import com.example.trainingProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepoImpl userRepo;

    @Autowired
    public UserServiceImpl(UserRepoImpl userRepoSpring) {
        this.userRepo = userRepoSpring;
    }


    public List<User> findBetween(Long with,Long by) {
        return userRepo.findBetween(with,by);
    }

    public User findById(Long id){
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()){
            return userOptional.get();
        }
        throw new RuntimeException("User not found"); // не знаю как по другому реализовать
    }
}
