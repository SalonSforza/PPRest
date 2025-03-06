package com.example.PPSecure.controller;

import jakarta.persistence.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping ("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/authUser")
    public String authUser() {
        return "authUser";
    }

    @GetMapping ("/login")
    public String login() {
        return "login";
    }

}
