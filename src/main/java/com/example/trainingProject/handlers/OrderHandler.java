package com.example.trainingProject.handlers;

import com.example.trainingProject.exceptions.OrderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderHandler {
    @ExceptionHandler(OrderException.class)
    public String handleException() {
        return "errorNewOrder";
    }
}
