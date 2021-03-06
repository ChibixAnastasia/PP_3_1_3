package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
     void addUser(User user);
     List<User> getAllUsers();
     User getUser(long id);
     void deleteUser(long id);
     void updateUser( User user);
}
