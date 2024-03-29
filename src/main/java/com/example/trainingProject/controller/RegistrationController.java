package com.example.trainingProject.controller;

import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addNewUser(UserDto userDto ){
        userService.createUser(userDto);
        return "redirect:/login";
    }
}
