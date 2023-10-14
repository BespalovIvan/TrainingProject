import service.Printer;
import service.Reader;
import service.impl.FileReader;
import service.impl.MessagePrinter;

public class Main {
    public static void main(String[] args) {
        Reader fileReader = new FileReader("C:\\Test\\Hello.txt");
        Printer messagePrinter = new MessagePrinter((FileReader) fileReader);
        messagePrinter.print();
    }
}