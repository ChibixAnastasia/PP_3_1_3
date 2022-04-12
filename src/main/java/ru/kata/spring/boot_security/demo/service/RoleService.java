package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoEntityManagerImpl;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.ArrayList;
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
    public Role getRoleByName(String name) {
        return roleDaoEntityManager.getRoleByName(name);
    }

    public List<Role> getSetRolesByNames(String[] roles) {
        List<Role> res = new ArrayList<>();
        System.err.println(res.toString());
        for (String s : roles) {
            res.add(getRoleByName(s));
        }
        return res;
    }
    public List<Role> getSetRolesByIds(List<Long> roles) {
        List<Role> res = new ArrayList<>();
        for (Long s : roles) {
            res.add(getRoleById(s));
        }
        return res;
    }

}
