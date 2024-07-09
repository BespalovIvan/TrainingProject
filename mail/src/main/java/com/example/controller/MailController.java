package com.example.controller;

import com.example.dto.UserDto;
import com.example.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activate")
@AllArgsConstructor
public class MailController {
    private final MessageService messageService;
    private final ConsumerFactory<String, UserDto> consumerFactory;

    @PostMapping
    @KafkaListener(topics = "topic-1", groupId = "group-1", containerFactory = "kafkaListenerContainerFactory")
    public String sendMessage(UserDto userDto) {
        return messageService.sendMessage(userDto);
    }
}


