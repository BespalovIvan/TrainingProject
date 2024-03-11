package com.example.trainingProject.controller;


import com.example.trainingProject.config.MyUserDetails;
import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String findById(@AuthenticationPrincipal MyUserDetails user, Model model) {
        Optional<UserDto> optionalUserDto = userService.findById(user.getUserId());
        if (optionalUserDto.isPresent()) {
            model.addAttribute("user", optionalUserDto.get());
        } else {
            model.addAttribute("user", new User());
        }
        return "user";
    }
}
