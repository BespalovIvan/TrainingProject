package service.impl;

import entity.Car;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.Printer;
import service.Reader;

public class JsonPrinter implements Printer {

    private final Reader reader;

    Car car;

    public JsonPrinter(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void print() {
        try {
            String messageJson = reader.read();
            if(messageJson == null || messageJson.isEmpty() ){
                throw new IllegalArgumentException("File is empty");
            }
            JSONObject jo = (JSONObject) new JSONParser().parse(messageJson);
            car = new Car((String) jo.get("name"),(String) jo.get("model"),(Double) jo.get("price"),
                    (boolean)jo.get("isNew"));
            System.out.println(car);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
