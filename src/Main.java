import entity.Car;
import service.Printer;
import service.Reader;
import service.impl.CarPrinter;
import service.impl.CarReader;

public class Main {
    public static void main(String[] args) {
        Reader<Car> carReader = new CarReader("C:\\Test\\JSONTest.json");
        Printer carPrinter = new CarPrinter(carReader);
        carPrinter.print();
    }
}