package com.example.service.impl;

import com.example.EmailException;
import com.example.dto.UserDto;
import com.example.service.SmtpMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class SmtpMailSenderImpl implements SmtpMailSender {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${mail.url.activate}")
    private String url;

    public SmtpMailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    @Override
    public String createMessageForActivate(UserDto userDto) {
        if (StringUtils.isEmpty(userDto.getEmail())) {
            throw new EmailException("Email not found!");

        } else {
            return String.format("Hello, %s! \n" +
                            "Welcome to Shop. Please, visit next link: " + url + "/%s"
                    , userDto.getName(), userDto.getActivateCode());
        }

    }
}
