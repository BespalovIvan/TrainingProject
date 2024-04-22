package com.example.trainingProject.handlers;

import com.example.trainingProject.exceptions.EmailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailHandler {
    @ExceptionHandler(EmailException.class)
    public String handleEmailException() {
        return "emailException";
    }
}
