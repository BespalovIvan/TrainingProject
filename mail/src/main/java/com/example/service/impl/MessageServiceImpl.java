package com.example.service.impl;

import com.example.EmailException;
import com.example.dto.UserDto;
import com.example.service.MessageService;
import com.example.service.SmtpMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class MessageServiceImpl implements MessageService {
    private final SmtpMailSender smtpMailSender;
    @Value("${mail.url.activate}")
    private String url;

    public MessageServiceImpl(SmtpMailSender smtpMailSender) {
        this.smtpMailSender = smtpMailSender;
    }

    @Override
    public String sendMessage(UserDto userDto) {
        if (StringUtils.isEmpty(userDto.getEmail())) {
            throw new EmailException("Email not found!");

        }
        String message = String.format("Hello, %s! \n" +
                        "Welcome to Shop. Please, visit next link: " + url + "/%s"
                , userDto.getName(), userDto.getActivateCode());
        smtpMailSender.send(userDto.getEmail(), "Activate code", message);
        return message;
    }
}
