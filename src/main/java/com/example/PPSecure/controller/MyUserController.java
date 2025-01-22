package com.example.PPSecure.controller;

import com.example.PPSecure.model.MyUser;
import com.example.PPSecure.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyUserController {

    MyUserDetailsService myUserDetailsService;

    @Autowired
    public MyUserController(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @GetMapping("/users")
    public String showAll(Model model) {
        model.addAttribute("users", myUserDetailsService.findAll());
        return "allUsers";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("myUser", new MyUser());
        return "userForm";
    }
    @PostMapping ("/newUser")
    public String saveUser(@ModelAttribute("user") MyUser user) {
        myUserDetailsService.persist(user);
        return "redirect:/login";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        myUserDetailsService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user")
    public String editUser(@RequestParam ("id") long id, Model model) {
        model.addAttribute("user", myUserDetailsService.findById(id));
        model.addAttribute("allRoles", myUserDetailsService.findAllRoles());
        return "editUser";
    }

    @PostMapping("/user")
    public String updateUser(@ModelAttribute("user") MyUser user,@RequestParam ("id") long id) {
        myUserDetailsService.update(user,id);
        return "redirect:/users";
    }

    @GetMapping("/authUser/{id}")
    public String authUser(@PathVariable long id, Model model) {
        model.addAttribute("user", myUserDetailsService.findById(id));
        return "authUser";
    }

    @GetMapping("/admin")
    public String adminLogIn(Model model) {
     model.addAttribute("myUser", new MyUser());
        return "admin";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("myUser", new MyUser());
       return "login";
    }



}
