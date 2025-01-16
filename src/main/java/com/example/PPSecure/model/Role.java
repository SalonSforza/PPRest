package com.example.PPSecure.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "role")
public class Role {
    @Id
    private long id;
    @Column
    private String roleDesignation;
    @ManyToMany (
            mappedBy = "roles"
    )
    private List<User> users;

    public Role() {
    }
}
