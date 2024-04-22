package com.example.trainingProject.service.impl;

public class EmailValidator {

    public static boolean isValid(String email) {
        return email.matches("^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$");
    }

}