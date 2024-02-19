package com.example.trainingProject.config;

import com.example.trainingProject.exceptions.ReaderException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Properties {
    private String path;

    public Properties(String path) {
        this.path = path;
    }

    public Properties() {
    }

    public Map<String, String> getProperties() {
        try {
            JSONObject ob = (JSONObject) new JSONParser().parse(new FileReader(path));
            HashMap<String, String> result = new HashMap<>();
            result.put("url", (String) ob.get("url"));
            result.put("login", (String) ob.get("login"));
            result.put("password", (String) ob.get("password"));
            return result;
        } catch (IOException | ParseException e) {
            throw new ReaderException("Failed read file");
        }
    }
}
