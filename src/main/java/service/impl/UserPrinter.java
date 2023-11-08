package service.impl;

import entity.User;
import service.Printer;
import service.Reader;

import java.util.List;

public class UserPrinter implements Printer {

    private final Reader<List<User>> reader;

    public UserPrinter(Reader<List<User>> reader) {
        this.reader = reader;
    }

    @Override
    public void print() {
        List<User> users =  reader.read();
        for(User user: users){
            System.out.println(user);
        }
    }
}
