package com.example.service.impl;

import com.example.dto.UserDto;
import com.example.service.MessageService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class KafkaListen {
    private final ConsumerFactory<String, UserDto> consumerFactory;
    private final MessageService messageService;

    @EventListener
    public void handleMessage(ListenerContainerIdleEvent event) {
        try (Consumer<String, UserDto> consumer = consumerFactory.createConsumer()) {
            consumer.subscribe(Collections.singletonList("topic-1"));
        }
    }

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listener(ConsumerRecord<String, UserDto> record) {

    }
}
