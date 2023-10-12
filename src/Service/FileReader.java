package Service;

import ServiceImpl.ReadableFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader implements ReadableFile {

    @Override
    public String readFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(path));
            return reader.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid file path");
        } catch (NullPointerException e) {
            throw new RuntimeException("File path is null");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file");
        }
    }
}

