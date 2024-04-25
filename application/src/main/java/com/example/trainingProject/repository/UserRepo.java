package com.example.trainingProject.repository;

import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.entity.User;

import java.util.Optional;

public interface UserRepo {
    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    Long createUser(UserDto userDto);

    Long updateUser(UserDto userDto);

    Optional<User> findNotActivatedUserByCode(String code);

    void generateActivateCode (Long userId, String activate_code);

    void activateUser(UserDto userDto);

}
