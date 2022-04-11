package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    //C: добавить пользователя в БД
    //R: Получить пользователя из БД
    //U: Обновить пользователя в БД
    //D: Удалить пользователя в БД

    void saveUser(String name, String lastName);
    void saveUser(User user);
    void updateUser(User user);
    void updateUser(long id, String name, String lastName);
    void removeUserById(long id);
    User readUserById(long id);
    List<User> getAllUsers();

}
