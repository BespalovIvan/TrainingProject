package service.impl;

import entity.Car;
import exceptions.ReaderException;
import service.Reader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CarReader implements Reader <Car> {

    private final String path;

    public CarReader(String path) {
        if (path == null|| path.trim().isEmpty()){
            throw new ReaderException ("The path to the file is incorrect. Meaning " + path);
        }
        this.path = path;
    }

    @Override
    public Car read() {
        try {
           return createCar((JSONObject) new JSONParser().parse(new FileReader(path)));
        }catch (ParseException e) {
            throw new ReaderException("Incorrect structure JSON");
        }catch (FileNotFoundException e) {
            throw new ReaderException("Invalid file path");
        }catch (NullPointerException e){
            throw new ReaderException("Not enough arguments");
        }catch (ClassCastException e){
            throw new ReaderException("Incorrect parameter type");
        }catch (IOException e) {
            throw new ReaderException("Failed to read file");
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
