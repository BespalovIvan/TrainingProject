package com.example.trainingProject.controller;

import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;

    private final RestTemplate restTemplate;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@RequestBody UserDto userDto, Model model) {
        if (!userService.createUser(userDto)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        userService.encodingUserPassword(userDto);
        HttpEntity<UserDto> userDtoHttpEntity = new HttpEntity<>(userDto);
        restTemplate.postForEntity("http://localhost:8081/activate",userDtoHttpEntity,String.class);

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "activation code is not found");
        }
        return "login";
    }
}

