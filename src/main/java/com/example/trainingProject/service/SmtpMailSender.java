package com.example.trainingProject.service;

public interface SmtpMailSender {
    void send(String emailTo, String subject,String message);
}
