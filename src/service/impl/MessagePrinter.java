package service.impl;

import service.Printer;

public class MessagePrinter implements Printer {
    FileReader reader;

    public void print() {
        String fileMessage = reader.readFile(reader.getPath());
        if (fileMessage == null || fileMessage.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        System.out.println(fileMessage);
    }

    public MessagePrinter(FileReader reader) {
        this.reader = reader;
    }
}
