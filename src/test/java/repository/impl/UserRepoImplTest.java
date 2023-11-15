package repository.impl;

import config.JDBCConnect;
import config.Properties;
import entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserRepoImplTest {

    @Test
    public void findAll() {
        UserRepoImpl userRepo = new UserRepoImpl(new JDBCConnect(new Properties("C:\\Test\\JSONTest.json")));
        List<User> expected = userRepo.findAll();
        List<User> actual = new ArrayList<>();
        User user = new User(1,"IVAN","BESPALOV","bespalovivan@mail.ru");
        actual.add(user);
        Assert.assertEquals(expected,actual);


    }

    @Test
    public void findById() {
    }

    @Test
    public void createUser() {
    }

    @Test
    public void updateUserById() {
    }

    @Test
    public void deleteUserById() {
    }
}