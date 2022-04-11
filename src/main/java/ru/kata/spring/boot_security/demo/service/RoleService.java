package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoEntityManagerImpl;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleDaoEntityManagerImpl roleDaoEntityManager;

    public Role getRoleById(long id) {
        return roleDaoEntityManager.getById(id);
    }

    public List<Role> findAll() {
        return roleDaoEntityManager.findAll();
    }

}
