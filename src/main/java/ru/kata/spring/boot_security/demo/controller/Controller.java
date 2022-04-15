package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@org.springframework.stereotype.Controller

@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
@AllArgsConstructor
@Data
public class Controller {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @GetMapping("/saveUser")
    public String save(@ModelAttribute("user") User user) {
        userService.addUser(user);   //saveOrUpdate in hibernate, persist in CRUD
        return "redirect: /admin";
    }

    @GetMapping("/updateUser")
    public String update(@RequestParam("userIDinRequestParam") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "newUser";
    }

    @DeleteMapping("/deleteUser")
    public String delete(@PathVariable("userIDinRequestParam") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
