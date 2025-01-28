package com.example.PPSecure.repositories;

import com.example.PPSecure.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role,Long> {

    @Query("SELECT r FROM Role r WHERE r.roleDesignation = 'ROLE_USER'")
    public Role findDefaultRole();
}
