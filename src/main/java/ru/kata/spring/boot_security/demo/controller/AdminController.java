package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
@AllArgsConstructor
@Data
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //GET table admin
    @GetMapping("")
    public String index(ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("principal", principal);
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "index";
    }

    //работает
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    //POST создание пользователя
    @PostMapping
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "rolesFromCreateUser") ArrayList<Long> roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleService.getSetRolesByIds(roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.readUserById(id));
        return "show";
    }

    //работает
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.readUserById(id));
        return "/edit";
    }

    //не работает
    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        System.err.println("WOW");
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    //работает
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin/";
    }

}
