package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String homePage(@AuthenticationPrincipal User user, Model model, Principal principal) {
        model.addAttribute("principal", principal);
        model.addAttribute("user", userService.getUser(user.getId()));
        return "second";
    }

}

/*
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String homePage() {
        return "user";
    }

}*/
