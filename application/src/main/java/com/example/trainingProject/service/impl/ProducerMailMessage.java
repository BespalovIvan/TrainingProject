package com.example.trainingProject.service.impl;

import com.example.trainingProject.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerMailMessage {
    private final KafkaTemplate<String, UserDto> kafkaTemplate;
    private static final String TOPIC = "topic-1";

    public void sendMessage(UserDto userDto) {
        Message<UserDto> message = MessageBuilder
                .withPayload(userDto)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        kafkaTemplate.send(message);
    }
}
