package com.example.trainingProject.service;

import com.example.trainingProject.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> findById(Long id);

    boolean createUser(UserDto userDto);

    boolean activateUser(String code);

    void encodingUserPassword(UserDto userDto);

}
