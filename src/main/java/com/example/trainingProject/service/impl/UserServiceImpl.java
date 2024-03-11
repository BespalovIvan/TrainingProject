package com.example.trainingProject.service.impl;

import com.example.trainingProject.config.MyUserDetails;
import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.entity.Role;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.repository.UserRepo;
import com.example.trainingProject.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepoSpring) {
        this.userRepo = userRepoSpring;
    }

    public Optional<UserDto> findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(value -> new UserDto(value.getName(), value.getEmail()));
    }

    public boolean createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepo.findByName(userDto.getName());
        if (optionalUser.isPresent()) {
            return false;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDto.setCreateDate(LocalDateTime.now());
        userDto.setRole(Role.USER.toString());
        userRepo.createUser(userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getCreateDate(), userDto.getRole());
        return true;
    }

    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepo.findByName(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "There is not such user in Repo"));
    }
}
