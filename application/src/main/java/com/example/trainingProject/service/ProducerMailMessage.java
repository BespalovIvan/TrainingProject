package com.example.trainingProject.service;

import com.example.trainingProject.dto.UserDto;

public interface ProducerMailMessage<K,V> {
    void sendMessage(V value);
}
