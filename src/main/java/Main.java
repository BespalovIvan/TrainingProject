
import entity.Car;
import service.Printer;
import service.Reader;
import service.impl.CarPrinter;
import service.impl.CarReader;

public class Main {
    public static void main(String[] args) {
        Reader<Car> carReader = new CarReader(args[0]);
        Printer carPrinter = new CarPrinter(carReader);
        carPrinter.print();
    }
}