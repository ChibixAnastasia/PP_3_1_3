package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoEntityManagerImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(String name, String lastName) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        entityManager.persist(user);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
//        entityManager.find(User.class, user.getId());
//        entityManager.merge(new User(user.getId(), user.getName(), user.getLastName()));
        entityManager.merge(user);
    }

    @Override
    public void updateUser(long id, String name, String lastName) {
        User user = entityManager.find(User.class, id);
        user.setName(name);
        user.setLastName(lastName);
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User readUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public User readUserByName(String name) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class);

        User user = query.setParameter("name", name)
                .getSingleResult();
        return user;
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user",
                User.class).getResultList();
    }

}
