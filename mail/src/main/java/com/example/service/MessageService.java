package com.example.service;

import com.example.dto.UserDto;

public interface MessageService {
    String sendMessageToMail(UserDto userDto);
}

