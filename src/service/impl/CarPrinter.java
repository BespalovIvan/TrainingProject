package service.impl;

import entity.Car;
import service.Printer;
import service.Reader;

public class CarPrinter implements Printer {

    private final Reader<Car> reader;

    public CarPrinter(Reader<Car> reader) {
        this.reader = reader;
    }

    @Override
    public void print() {
            Car car = reader.read();
            if(car == null) {
                throw new IllegalArgumentException("File is empty");
            }
            System.out.println(car);
    }
}
