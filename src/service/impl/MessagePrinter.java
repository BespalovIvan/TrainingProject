package service.impl;

import service.Printer;
import service.Reader;

public class MessagePrinter implements Printer {
    Reader reader;

    public void print() {
        String fileMessage = reader.readFile(path);
        if (fileMessage == null || fileMessage.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        System.out.println(fileMessage);
    }

    public MessagePrinter(Reader reader) {
        this.reader = reader;
    }
}
