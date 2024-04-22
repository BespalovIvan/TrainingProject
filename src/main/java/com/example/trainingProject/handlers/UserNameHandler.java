package com.example.trainingProject.handlers;

import com.example.trainingProject.exceptions.UserNameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNameHandler {
    @ExceptionHandler(UserNameException.class)
    public String handleUserNameException() {
        return "userNameException";
    }
}
