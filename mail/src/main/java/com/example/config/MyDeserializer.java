package com.example.config;

import com.example.dto.GeneralData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class MyDeserializer extends JsonDeserializer<GeneralData> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T deserialize(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

}
