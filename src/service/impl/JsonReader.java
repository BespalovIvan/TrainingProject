package service.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.Reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class JsonReader implements Reader {

    private final String path;

    public JsonReader(String path) {

        this.path = path;
    }

    @Override
    public String read() {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(path));
            return jo.toString();
        } catch (ParseException e) {
            throw new RuntimeException("failed to parse file");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found");
        } catch (IOException e) {
            throw new RuntimeException("failed to read file");
        }
    }


}
