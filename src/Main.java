import Service.FilePrinter;

public class Main {
    public static void main(String[] args)  {
        FilePrinter printer = new FilePrinter();
        printer.printMessage("C:\\Test\\Hello.txt");
    }
}