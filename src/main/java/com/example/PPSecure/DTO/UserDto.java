package com.example.PPSecure.DTO;

import java.util.Set;

public class UserDto {

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

    private Set<RoleDto> roles;


    public UserDto(String username, int age, long id, Set<RoleDto> roles) {
        this.username = username;
        this.age = age;
        this.roles = roles;
        this.id = id;
    }

    public UserDto() {
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

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
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
