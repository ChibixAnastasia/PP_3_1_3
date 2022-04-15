package ru.kata.spring.boot_security.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements  UserDetailsService {
    private UserDaoImpl userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    @Transactional
    public void addUser(User user){
        this.userDao.addUser(user);
    }

    @Transactional
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();

    }

    @Transactional
    public User getUser(long id) {
        return this.userDao.getUser(id);
    }

    @Transactional
    public void deleteUser(long id) {
        this.userDao.deleteUser(id);
    }

    @Transactional
    public void updateUser( User user) {
        this.userDao.updateUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.readUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.getRoles().size();
        return user;
    }
    }

