package com.example.trainingProject.handlers;

import com.example.trainingProject.exceptions.PasswordException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PasswordHandler {
    @ExceptionHandler(PasswordException.class)
    public String handlePasswordException() {
        return "passwordException";
    }
}
