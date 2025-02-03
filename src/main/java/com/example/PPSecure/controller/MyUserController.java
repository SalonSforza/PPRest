package com.example.PPSecure.controller;

import com.example.PPSecure.model.MyUser;
import com.example.PPSecure.services.MyUserDetailsServiceImpl;
import com.example.PPSecure.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyUserController {

    MyUserDetailsServiceImpl myUserDetailsServiceImpl;
    RoleServiceImpl roleServiceImpl;

    @Autowired
    public MyUserController(MyUserDetailsServiceImpl myUserDetailsServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.myUserDetailsServiceImpl = myUserDetailsServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/users")
    public String showAll(Model model) {
        model.addAttribute("users", myUserDetailsServiceImpl.findAll());
        return "allUsers";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("myUser", new MyUser());
        return "userForm";
    }
    @PostMapping ("/newUser")
    public String saveUser(@ModelAttribute("user") MyUser user) {
        myUserDetailsServiceImpl.persist(user);
        return "redirect:/login";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        myUserDetailsServiceImpl.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user")
    public String editUser(@RequestParam ("id") long id, Model model) {
        model.addAttribute("user", myUserDetailsServiceImpl.findById(id));
        model.addAttribute("allRoles", roleServiceImpl.findAllRoles());
        return "editUser";
    }

    @PostMapping("/user")
    public String updateUser(@ModelAttribute("user") MyUser user,@RequestParam ("id") long id) {
        myUserDetailsServiceImpl.update(user,id);
        return "redirect:/users";
    }

    @GetMapping("/authUser/{id}")
    public String authUser(@PathVariable long id, Model model) {
        model.addAttribute("user", myUserDetailsServiceImpl.findById(id));
        return "authUser";
    }

    @GetMapping("/admin")
    public String adminLogIn(Model model) {
        model.addAttribute("user", myUserDetailsServiceImpl.findLoggedInAdmin());
        return "admin";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("myUser", new MyUser());
       return "login";
    }



}
