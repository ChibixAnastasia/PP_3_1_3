package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public Role getById(long id){
        return entityManager.find(Role.class, id);
    }

    public List<Role> findAll(){
        return entityManager.createQuery("select role from Role role",
                Role.class).getResultList();
    }

    public Role getByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM User r WHERE r.name = :name", Role.class);
        Role role = query.setParameter("name", name)
                .getSingleResult();
        return role;
    }
}
