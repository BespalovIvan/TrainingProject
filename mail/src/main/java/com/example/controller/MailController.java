package com.example.controller;

import com.example.dto.UserDto;
import com.example.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send-message")
@AllArgsConstructor
public class MailController {
    private final MessageService messageService;

    @PostMapping
    public String sendMessage(@RequestBody UserDto userDto) {
        return messageService.sendMessage(userDto);
    }
}
