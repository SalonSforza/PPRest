package com.example.PPSecure.services;

import com.example.PPSecure.DTO.RoleDTO;
import com.example.PPSecure.model.Role;
import com.example.PPSecure.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Set<Role> findAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Set<RoleDTO> findRoleDTOs() {
        Set<RoleDTO> rolesDTOs = new HashSet<>();
        Set<Role> roles = findAllRoles();
        ModelMapper modelMapper = new ModelMapper();
        for(Role role : roles) {
            rolesDTOs.add(modelMapper.map(role, RoleDTO.class));

        }
        return rolesDTOs;
    }


}
