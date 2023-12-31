package config;

import exceptions.ReaderException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Properties {
    private final String path;

    public Properties(String path) {
        this.path = path;
    }

    public Map<String,String> getProperties(){
        try {
            JSONObject ob = (JSONObject) new JSONParser().parse(new FileReader(path));
            HashMap<String,String> result = new HashMap<>();
            result.put("url",(String) ob.get("url"));
            result.put("login",(String)ob.get("login"));
            result.put("password",(String)ob.get("password"));
            return result;
        } catch (IOException | ParseException e) {
            throw new ReaderException("Failed read file");
        }
    }
}
