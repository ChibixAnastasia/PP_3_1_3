package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDaoEntityManagerImpl;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {
    final UserDaoEntityManagerImpl userDaoEntityManagerImpl;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDaoEntityManagerImpl userDaoEntityManagerImpl, PasswordEncoder passwordEncoder) {
        this.userDaoEntityManagerImpl = userDaoEntityManagerImpl;
        this.passwordEncoder = passwordEncoder;
    }
    public List<User> getAllUsers() {
        return userDaoEntityManagerImpl.getAllUsers();
    }

    public User readUserById(Long id) {
        return userDaoEntityManagerImpl.readUserById(id);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDaoEntityManagerImpl.saveUser(user);
    }

    public void removeUserById(long id) {
        userDaoEntityManagerImpl.removeUserById(id);
    }

    public void updateUser(User user) {
        userDaoEntityManagerImpl.saveUser(user);
        userDaoEntityManagerImpl.updateUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDaoEntityManagerImpl.readUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.getRoles().size();
        return user;
    }
}