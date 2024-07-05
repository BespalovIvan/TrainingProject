package com.example.trainingProject.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopic {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("topic-1").build();
    }
}
