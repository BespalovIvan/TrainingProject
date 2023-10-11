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
            throw new RuntimeException("Invalid file path");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file");
        }
    }

    public static void showInfo(String path) {
        try {
            String content = readFile(path);
            System.out.println(content);
        } catch (NullPointerException e) {
            throw new RuntimeException("File is empty");
        }
    }
}
