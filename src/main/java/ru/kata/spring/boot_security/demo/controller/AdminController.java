package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@AllArgsConstructor
@Data
public class AdminController {

    @GetMapping("")
    public String admin() {
        return "first";
    }
}
