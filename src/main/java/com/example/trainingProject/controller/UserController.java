package com.example.trainingProject.controller;


import com.example.trainingProject.entity.User;
import com.example.trainingProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findUsers(@RequestParam("with") Long with, @RequestParam("by") Long by, Model model) {
        List<User> userList = userService.findBetween(with, by);
        model.addAttribute("users", userList);
        return "user-list";
    }

    @GetMapping("/user")
    public String findById(@RequestParam("id") Long id, Model model) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
        } else {
            model.addAttribute("user", new User());
        }
        return "user";
    }
}
