package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private EntityManager manager;


    @Override
    public void addUser(User user){
        manager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list =  manager.createQuery("SELECT user FROM User user", User.class).getResultList();
        return list;
    }

    @Override
    public User getUser(long id){
       User user = manager.find(User.class, id);
       //manager.detach(user);
        return user;
    }

    @Override
    public void deleteUser(long id) {
        User user = manager.find(User.class, id);
        manager.remove(user);
    }

    @Override
    public void updateUser( User user) {
       // manager.detach(user);
        manager.merge(user);
    }

    public User readUserByName(String name) {

        TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class);

        User user = query.setParameter("name", name)
                .getSingleResult();
        return user;
    }
}
