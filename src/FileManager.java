import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    private static String readFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            return reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file path");
        } catch (IOException e) {
            System.out.println("failed to read file");
        }
        return "Failure(";
    }

    public static void ShowInfo(String path) {
        String content = readFile(path);
        if (content == null) {
            System.out.println("File is empty");
        } else {
            System.out.println(content);
        }
    }
}
