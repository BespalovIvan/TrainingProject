package service.impl;

import entity.Car;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.Reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CarReader implements Reader <Car> {

    private final String path;

    public CarReader(String path) {

        this.path = path;
    }

    @Override
    public Car read() {
        if (path == null){
            throw new RuntimeException ("file path is null");
        }
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(path));
            return new Car((String) jo.get("name"), (String) jo.get("model"), (Double) jo.get("price"),
                    (boolean) jo.get("isNew"));
        }catch (ParseException e) {
            throw new RuntimeException("Incorrect structure JSON");
        }catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid file path");
        }catch (NullPointerException e){
            throw new RuntimeException("Not enough arguments");
        }catch (ClassCastException e){
            throw new RuntimeException("Incorrect parameter type");
        }catch (IOException e) {
            throw new RuntimeException("Failed to read file");
        }
    }
}
