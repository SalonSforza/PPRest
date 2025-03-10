package com.example.PPSecure.DTO;

public class RoleDto {

    public RoleDto() {
    }

    private String roleDesignation;

    private long id;

    public RoleDto(String roleDesignation) {
        this.roleDesignation = roleDesignation;
    }

    public RoleDto(String roleDesignation, long id) {
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
