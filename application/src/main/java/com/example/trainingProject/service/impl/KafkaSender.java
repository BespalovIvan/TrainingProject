package com.example.trainingProject.service.impl;

import com.example.trainingProject.dto.UserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(UserDto userDto) {

        kafkaTemplate.send("topic-1", userDto);
    }
}
