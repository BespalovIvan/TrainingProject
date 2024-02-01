package repository;

import entity.User;

import java.util.List;

public interface UserRepo{
    List<User> findAll();
    User findById(Long id);
    void createUser(String name,String email);
    void updateUserById(Long id, String name,String email);
    void deleteUserById(Long id);
}
