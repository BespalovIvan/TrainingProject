package com.example.trainingProject.controller;

import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.exceptions.EmailException;
import com.example.trainingProject.exceptions.PasswordException;
import com.example.trainingProject.exceptions.UserNameException;
import com.example.trainingProject.service.SmtpMailSender;
import com.example.trainingProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final SmtpMailSender smtpMailSender;

    public RegistrationController(UserService userService, SmtpMailSender smtpMailSender) {
        this.userService = userService;
        this.smtpMailSender = smtpMailSender;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(UserDto userDto, Model model) {
        if (!userService.createUser(userDto)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        userService.encodingUserPassword(userDto);
        String message = smtpMailSender.createMessageForActivate(userDto);
        smtpMailSender.send(userDto.getEmail(), "Activate code", message);
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

    @ExceptionHandler(PasswordException.class)
    public String handlePasswordException() {
        return "passwordException";
    }
    @ExceptionHandler(EmailException.class)
    public String handleEmailException() {
        return "emailException";
    }
    @ExceptionHandler(UserNameException.class)
    public String handleUserNameException() {
        return "userNameException";
    }
}

