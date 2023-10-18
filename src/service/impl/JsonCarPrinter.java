package service.impl;

import entity.Car;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.Printer;
import service.Reader;

public class JsonCarPrinter implements Printer {

    private final Reader reader;

    public JsonCarPrinter(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void print() {
        try {
            String messageJson = reader.read();
            if(messageJson.equals("{}") ){
                throw new IllegalArgumentException("File is empty");
            }
            JSONObject jo = (JSONObject) new JSONParser().parse(messageJson);
            Car car = new Car((String) jo.get("name"), (String) jo.get("model"), (Double) jo.get("price"),
                    (boolean) jo.get("isNew"));
            System.out.println(car);

        } catch (ParseException e) {
            throw new RuntimeException("failed parse json from string");
        }
        catch (ClassCastException e){
            throw new RuntimeException("invalid parameter type");
        }
        catch (NullPointerException e){
            throw new RuntimeException("Not all parameters have been entered");
        }

    }
}
