package com.example.trainingProject.controller;


import com.example.trainingProject.entity.User;
import com.example.trainingProject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {


    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findUsers (@RequestParam("with") Long with, @RequestParam("by") Long by, Model model){
        List<User> userList = userService.findBetween(with,by);
        model.addAttribute("users",userList);
        return "user-list";
    }
}
