package entity;

import java.util.Objects;

public class Car {
    private final String name;
    private final String model;
    private final Double price;
    private final boolean isNew;

    public Car(String name, String model, Double price, boolean isNew) {
        this.name = name;
        this.model = model;
        this.price = price;
        this.isNew = isNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isNew == car.isNew && Objects.equals(name, car.name) && Objects.equals(model, car.model) && Objects.equals(price, car.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, price, isNew);
    }

    @Override
    public String toString() {
        return "Car {" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", isNew=" + isNew +
                '}';
    }
}
