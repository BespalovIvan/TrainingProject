package com.example.trainingProject.service.impl;

import com.example.trainingProject.service.ProducerMailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProducerMailMessageImpl<K, V> implements ProducerMailMessage<K, V> {
    private final KafkaTemplate<K, V> kafkaTemplate;
    @Value("kafka.mail.topic.name")
    private String topicName;

    public ProducerMailMessageImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(V value) {
        Message<V> message = MessageBuilder
                .withPayload(value)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }
}
