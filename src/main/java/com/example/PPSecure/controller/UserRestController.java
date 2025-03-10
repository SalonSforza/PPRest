package com.example.PPSecure.controller;

import com.example.PPSecure.DTO.RoleDto;
import com.example.PPSecure.DTO.UserDto;
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


    UserServiceImpl userServiceImpl;
    RoleServiceImpl roleServiceImpl;
    ModelMapper modelMapper;

    @Autowired
    public UserRestController(UserServiceImpl UserServiceImpl, RoleServiceImpl roleServiceImpl, ModelMapper modelMapper, UserServiceImpl userServiceImpl) {
        this.userServiceImpl = UserServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.modelMapper = modelMapper;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public List<UserDTOtoSend> showAll() {
        return userServiceImpl.findAll();
    }

    @GetMapping("/newUser")
    public UserDto newUser() {
        return new UserDto() ;
    }

    @PostMapping ("/newUser")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserDto userDto) {
        userServiceImpl.save(covertToUser(userDto));
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    @DeleteMapping("/users/{Id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int Id) {
        userServiceImpl.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user")
    public  ResponseEntity<HttpStatus> updateUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        userServiceImpl.update(covertToUser(userDto), covertToUser(userDto).getId());
        return  new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping ("/user")
    public UserDTOtoSend getLoggedInUser(){
        return covertToUserDTOtoSend(userServiceImpl.findLoggedInUserByUserName());
    }

    @GetMapping ("/user/{id}")
    public UserDTOtoSend getUserById(@PathVariable int id) {
        return covertToUserDTOtoSend(userServiceImpl.findById(id));
    }

    @GetMapping ("/roles")
    public Set<RoleDto> getRoles() {
        return roleServiceImpl.getRoleDTOs();
    }


    public User covertToUser(UserDto dtOtoReceive) {
        return modelMapper.map(dtOtoReceive, User.class);
    }

    public User covertToUser(UserDTOtoSend dtOtoSend) {
        return modelMapper.map(dtOtoSend, User.class);
    }

    public UserDto covertToUserDTOtoReceive(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public UserDTOtoSend covertToUserDTOtoSend(User user) {
        return modelMapper.map(user, UserDTOtoSend.class);
    }

    public RoleDto covertToRoleDTOtoRole(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    public Role covertToRole(RoleDto dto) {
        return modelMapper.map(dto, Role.class);
    }




}
