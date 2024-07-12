package com.example.listeners;

import com.example.dto.UserDto;
import com.example.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaMailListener {
    private final MessageService messageService;
    private final ConsumerFactory<String, UserDto> consumerFactory;

    @KafkaListener(topics = "kafka.mail.topic.name", groupId = "group-1",
            containerFactory = "kafkaListenerContainerFactory")
    public String sendMail(UserDto userDto) {
        return messageService.sendMessageToMail(userDto);
    }
}
