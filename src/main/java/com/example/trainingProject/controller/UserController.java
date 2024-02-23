package com.example.trainingProject.controller;


import com.example.trainingProject.entity.User;
import com.example.trainingProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findUsers(@RequestParam(value = "with", defaultValue = "1") Long with,
                            @RequestParam(value = "by", defaultValue = "100000") Long by, Model model) {
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

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "create-user";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.createUser(user.getFirstName(), user.getEmail());
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
        }
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUserById(user.getId(), user.getFirstName(), user.getEmail());
        return "redirect:/users";
    }

}
