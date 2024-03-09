package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.User;
import com.example.trainingProject.repository.UserRepo;
import com.example.trainingProject.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepoSpring) {
        this.userRepo = userRepoSpring;
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public boolean createUser(User user) {
        Optional<User> optionalUser = userRepo.findByName(user.getName());
        if (optionalUser.isPresent()) {
            return false;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        user.setRole("USER");
        userRepo.createUser(user.getName(), user.getEmail(), user.getPassword(), user.getCreateDate(), user.getRole());
        return true;
    }
}
