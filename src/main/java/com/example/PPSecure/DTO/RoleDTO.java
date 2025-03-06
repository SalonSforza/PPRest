package com.example.PPSecure.DTO;

public class RoleDTO {

    public RoleDTO() {
    }

    private String roleDesignation;

    private long id;

    public RoleDTO(String roleDesignation) {
        this.roleDesignation = roleDesignation;
    }

    public RoleDTO(String roleDesignation, long id) {
        this.roleDesignation = roleDesignation;
        this.id = id;
    }

    public String getRoleDesignation() {
        return roleDesignation;
    }

    public void setRoleDesignation(String roleDesignation) {
        this.roleDesignation = roleDesignation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
