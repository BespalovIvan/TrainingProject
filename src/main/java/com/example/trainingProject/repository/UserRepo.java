package com.example.trainingProject.repository;

import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepo {
    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    Long createUser(UserDto userDto);
    Long updateUser(UserDto userDto);

    Optional<User> findUserByActivatedCode(String code);
}
