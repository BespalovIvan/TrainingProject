import entity.User;
import service.Printer;
import service.Reader;
import service.impl.UserPrinter;
import service.impl.UserReader;

import java.util.List;

public class Main {
    public static void main(String[] args)  {
        Reader<List<User>> userReader = new UserReader();
        Printer userPrinter = new UserPrinter(userReader);
        userPrinter.print();
    }
}