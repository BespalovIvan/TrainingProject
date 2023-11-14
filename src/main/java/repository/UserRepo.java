package repository;

import entity.User;

import java.util.List;

public interface UserRepo{
    List<User> findAll();
    User findById(Integer id);
    void createUser(String name,String lastName,String email);
    void updateUserById(Integer id, String name, String lastName, String email);
    void deleteUserById(Integer id);
}
