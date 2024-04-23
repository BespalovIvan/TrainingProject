package com.example.service;

import com.example.trainingProject.dto.UserDto;

public interface SmtpMailSender {
    void send(String emailTo, String subject,String message);
    String createMessageForActivate(UserDto userDto);
}
