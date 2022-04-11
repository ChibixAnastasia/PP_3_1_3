package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoEntityManagerImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public Role getById(long id){
        return entityManager.find(Role.class, id);
    }
    public List<Role> findAll(){
        return entityManager.createQuery("select role from Role role",
                Role.class).getResultList();
    }
}
