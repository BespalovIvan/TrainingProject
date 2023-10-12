import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    private String readFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            return reader.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid file path");
        } catch (NullPointerException e) {
            throw new RuntimeException("File path is null");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file");
        }
    }

    public void showInfo(String path) {
        String fileMessage = readFile(path);
        if (fileMessage == null || fileMessage.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        System.out.println(fileMessage);
    }
}
