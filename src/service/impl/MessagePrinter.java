package service.impl;

import service.Printer;
import service.Reader;

public class MessagePrinter implements Printer {
    private final Reader reader;

    public MessagePrinter(Reader reader) {
        this.reader = reader;
    }

    public void print() {
        String fileMessage = reader.read();
        if (fileMessage == null || fileMessage.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        System.out.println(fileMessage);
    }
}
