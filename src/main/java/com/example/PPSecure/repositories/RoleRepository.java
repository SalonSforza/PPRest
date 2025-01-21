package com.example.PPSecure.repositories;

import com.example.PPSecure.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role,Long> {
}
