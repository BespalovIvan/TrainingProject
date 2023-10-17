package entity;

public class Car {
    String name;
    String model;
    Double price;
    boolean isNew;

    public Car(String name, String model, Double price, boolean isNew) {
        this.name = name;
        this.model = model;
        this.price = price;
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", isNew=" + isNew +
                '}';
    }
}
