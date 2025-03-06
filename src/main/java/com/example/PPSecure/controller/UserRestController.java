package com.example.PPSecure.controller;

import com.example.PPSecure.DTO.RoleDTO;
import com.example.PPSecure.DTO.UserDTOtoReceive;
import com.example.PPSecure.DTO.UserDTOtoSend;
import com.example.PPSecure.model.Role;
import com.example.PPSecure.model.User;
import com.example.PPSecure.services.UserServiceImpl;
import com.example.PPSecure.services.RoleServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserServiceImpl userServiceImpl;
    UserServiceImpl UserServiceImpl;
    RoleServiceImpl roleServiceImpl;
    ModelMapper modelMapper;

    @Autowired
    public UserRestController(UserServiceImpl UserServiceImpl, RoleServiceImpl roleServiceImpl, ModelMapper modelMapper, UserServiceImpl userServiceImpl) {
        this.UserServiceImpl = UserServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.modelMapper = modelMapper;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public List<UserDTOtoSend> showAll() {
        return UserServiceImpl.findAll();
    }

    @GetMapping("/newUser")
    public UserDTOtoReceive newUser() {
        return new UserDTOtoReceive() ;
    }

    @PostMapping ("/newUser")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserDTOtoReceive userDTOtoReceive) {
        UserServiceImpl.save(covertToUser(userDTOtoReceive));
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    @DeleteMapping("/users/{Id}")
    public ResponseEntity<HttpStatus> findUserToDelete(@PathVariable int Id) {
        UserServiceImpl.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user")
    public  ResponseEntity<HttpStatus> updateUser(@RequestBody UserDTOtoReceive userDTOtoReceive) {
        System.out.println(userDTOtoReceive);
        User user = covertToUser(userDTOtoReceive);
        UserServiceImpl.update(user, user.getId());
        return  new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping ("/user")
    public UserDTOtoSend getLoggedInUser(){
        return covertToUserDTOtoSend(UserServiceImpl.findLoggedInUserByUserName());
    }

    @GetMapping ("/user/{id}")
    public UserDTOtoSend getUserById(@PathVariable int id) {
        return covertToUserDTOtoSend(UserServiceImpl.findById(id));
    }

    @GetMapping ("/roles")
    public Set<RoleDTO> getRoles() {
        return roleServiceImpl.findRoleDTOs();
    }


    public User covertToUser(UserDTOtoReceive dtOtoReceive) {
        return modelMapper.map(dtOtoReceive, User.class);
    }

    public User covertToUser(UserDTOtoSend dtOtoSend) {
        return modelMapper.map(dtOtoSend, User.class);
    }

    public UserDTOtoReceive covertToUserDTOtoReceive(User user) {
        return modelMapper.map(user, UserDTOtoReceive.class);
    }

    public UserDTOtoSend covertToUserDTOtoSend(User user) {
        return modelMapper.map(user, UserDTOtoSend.class);
    }

    public RoleDTO covertToRoleDTOtoRole(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    public Role covertToRole(RoleDTO dto) {
        return modelMapper.map(dto, Role.class);
    }




}
