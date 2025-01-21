package com.example.PPSecure.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
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

    private Set<MyUser> myUsers;

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

    public Set<MyUser> getMyUsers() {
        return myUsers;
    }

    public void setMyUsers(Set<MyUser> myUsers) {
        this.myUsers = myUsers;
    }
}
