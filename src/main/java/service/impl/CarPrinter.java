package service.impl;

import entity.Car;
import service.Printer;
import service.Reader;

import java.util.Objects;

public class CarPrinter implements Printer {

    private final Reader<Car> reader;

    public CarPrinter(Reader<Car> reader) {
        this.reader = reader;
    }

    @Override
    public void print() {
            Car car = reader.read();
        Objects.requireNonNull(car,"car cannot be null");
            System.out.println(car);
    }
}
