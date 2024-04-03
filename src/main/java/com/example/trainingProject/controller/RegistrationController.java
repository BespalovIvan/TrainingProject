package com.example.trainingProject.controller;

import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(UserDto userDto,Model model) {
        if(!userService.createUser(userDto)){
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate (Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if(isActivated){
            model.addAttribute("message","User successfully activated");
        }
        else {
            model.addAttribute("message","activation code is not found");
        }
        return "login";
    }
}
