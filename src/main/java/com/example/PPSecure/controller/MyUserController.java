package com.example.PPSecure.controller;

import com.example.PPSecure.model.MyUser;
import com.example.PPSecure.services.UserServiceImpl;
import com.example.PPSecure.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyUserController {

    UserServiceImpl myUserDetailsServiceImpl;
    RoleServiceImpl roleServiceImpl;

    @Autowired
    public MyUserController(UserServiceImpl myUserDetailsServiceImpl, RoleServiceImpl roleServiceImpl) {
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
        model.addAttribute("user", new MyUser());
        return "admin";
    }
    @PostMapping ("/newUser")
    public String saveUser(@ModelAttribute("user") MyUser user) {
        myUserDetailsServiceImpl.save(user);
        return "redirect:/admin";
    }
    // Проверить на необходимость или удалить потом
    @GetMapping("/delete")
    public String findUserToDeleteUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", myUserDetailsServiceImpl.findById(id));
        return "/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam ("id") long id) {
        myUserDetailsServiceImpl.deleteById(id);
        return "redirect:/admin";
    }
    @GetMapping("/user")
    public String editUser(@RequestParam ("id") long id, Model model) {
        MyUser user = myUserDetailsServiceImpl.findById(id);
        model.addAttribute("user",user);
        model.addAttribute("allRoles", roleServiceImpl.findAllRoles());
      return "admin";
    }

    @PostMapping("/user")
    public String updateUser(@ModelAttribute("user") MyUser user,@RequestParam ("id") long id) {
        myUserDetailsServiceImpl.update(user,id);
        return "redirect:/admin";
    }

    @GetMapping("/authUser")
    public String authUser( Model model) {
        model.addAttribute("user", myUserDetailsServiceImpl.findLoggedInUserByUserName());
        return "authUser";
    }

    @GetMapping("/admin")
    public String adminLogIn(Model model) {
        model.addAttribute("user1", myUserDetailsServiceImpl.findLoggedInUserByUserName());
        model.addAttribute("users", myUserDetailsServiceImpl.findAll());
        model.addAttribute("allRoles", roleServiceImpl.findAllRoles());
        model.addAttribute("newUser", new MyUser());
        model.addAttribute("user", new MyUser());
        return "admin";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("myUser", new MyUser());
       return "login";
    }




}
