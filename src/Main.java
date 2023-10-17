import service.Printer;
import service.Reader;
import service.impl.JsonPrinter;
import service.impl.JsonReader;

public class Main {
    public static void main(String[] args) {
        Reader jsonReader = new JsonReader("C:\\Test\\JSONTest.json");
        Printer jsonPrinter = new JsonPrinter(jsonReader);
        jsonPrinter.print();
    }
}