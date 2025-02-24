package com.example.PPSecure.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table (name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String roleDesignation;
    @ManyToMany (
            mappedBy = "roles"
    )

    private Set<User> users;

    public Role() {
    }

    public Role(String roleDesignation) {
        this.roleDesignation = roleDesignation;
    }

    @Override
    public String getAuthority() {
        return roleDesignation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleDesignation() {
        return roleDesignation;
    }

    public void setRoleDesignation(String roleDesignation) {
        this.roleDesignation = roleDesignation;
    }

    public Set<User> getMyUsers() {
        return users;
    }

    public void setMyUsers(Set<User> users) {
        this.users = users;
    }
}
