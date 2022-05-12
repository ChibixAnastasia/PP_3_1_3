package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
    public String homePage(@AuthenticationPrincipal User user, Model model,
                           Principal principal) {
        model.addAttribute("principal", principal);
        model.addAttribute("user", userService.getUser(user.getId()));
        return "user";
    }

}