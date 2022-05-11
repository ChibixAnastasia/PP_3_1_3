package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")

public class RestAdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //all users
    @GetMapping("/users")
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public User authorized(Principal principal){
        return (User) userService.loadUserByUsername(principal.getName());
    }

    //id user
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    //add new user
    @PostMapping("/users")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return String.valueOf(user.getId());
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void removeUserById(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.findAll();
    }
}
