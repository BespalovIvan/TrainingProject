package com.example.controller;

import com.example.dto.UserDto;
import com.example.service.SmtpMailSender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("activate")
@AllArgsConstructor
public class MailController {
    private final SmtpMailSender smtpMailSender;

    @PostMapping
    public String sendMessage(@RequestBody UserDto userDto) {
        String message = smtpMailSender.createMessageForActivate(userDto);
        smtpMailSender.send(userDto.getName(), "Activate code", message);
        return message;
    }
}
