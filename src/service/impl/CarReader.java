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
        if (path == null|| path.trim().isEmpty()){
            throw new RuntimeException ("The path to the file is incorrect. Meaning " + path);
        }
        this.path = path;
    }

    @Override
    public Car read() {
        try {
           return createCar((JSONObject) new JSONParser().parse(new FileReader(path)));
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

    private Car createCar(JSONObject jo){
        return new Car(
                (String) jo.get("name"),
                (String) jo.get("model"),
                (Double) jo.get("price"),
                (boolean) jo.get("isNew")
        );
    }
}
