package com.example.PPSecure.DTO;

import com.example.PPSecure.model.Role;

import java.util.Set;

public class UserDTOtoReceive {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    long id;

    private String username;

    private int age;

    private String password;

    private Set<RoleDTO> roles;


    public UserDTOtoReceive(String username, int age, long id, Set<RoleDTO> roles) {
        this.username = username;
        this.age = age;
        this.roles = roles;
        this.id = id;
    }

    public UserDTOtoReceive() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTOtoReceive{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", age=" + age +
               ", password='" + password + '\'' +
               ", roles=" + roles +
               '}';
    }
}
