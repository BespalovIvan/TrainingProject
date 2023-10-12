package Service;

import ServiceImpl.PrintableMessage;
import ServiceImpl.ReadableFile;

public class FilePrinter implements PrintableMessage {
    ReadableFile reader = new FileReader();
    public void printMessage(String path) {
        String fileMessage = reader.readFile(path);
        if (fileMessage == null || fileMessage.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        System.out.println(fileMessage);
    }
}
