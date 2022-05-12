package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@org.springframework.stereotype.Controller


@PreAuthorize("hasAnyRole('ADMIN')")
@AllArgsConstructor
@Data
public class Controller {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/admin")
    public String adminMain(ModelMap model){
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nameAdmin",admin);
        model.addAttribute("userAll",userService.getAllUsers());
        return "admin";
    }
    /*
    @GetMapping("/admin")
    public String admin(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "first";
    }*/

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/addUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @GetMapping("/saveUser")
    public String save(@ModelAttribute("user") User user) {
        userService.addUser(user);   //saveOrUpdate in hibernate, persist in CRUD
        return "redirect: /admin";
    }

   /* @PutMapping("/edit/{id}")
    public String update(@RequestParam("userIDinRequestParam") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "redirect:/admin";
    }*/
    @PutMapping("/edit/{id}")
    public String update(@ModelAttribute("user")User user,
                         @PathVariable("id")long id,
                         @RequestParam(value = "nameRoles")String[]nameRoles) {
        user.setRoles(roleService.getRolesByNames(nameRoles));
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("deleteUser/{id}")
    public String delete(@PathVariable("userIDinRequestParam") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
