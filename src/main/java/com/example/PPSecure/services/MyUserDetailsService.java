package com.example.PPSecure.services;

import com.example.PPSecure.model.MyUser;
import com.example.PPSecure.model.Role;
import com.example.PPSecure.repositories.MyUserRepository;
import com.example.PPSecure.repositories.RoleRepository;
import com.example.PPSecure.security.MyUserDetails;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MyUserRepository myUserRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public MyUserDetailsService( MyUserRepository myUserRepository, RoleRepository roleRepository) {
        this.myUserRepository = myUserRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUser = myUserRepository.findByUsername(username);
        if (myUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(myUser.get());
    }

    @Transactional
    public void persist(MyUser user) {
        Hibernate.initialize(user.getRoles());
        Optional<Role> userRole = roleRepository.findById(1L);
        if (userRole.isPresent()) {
            if (user.getRoles() == null) {
                user.setRoles(new HashSet<>());
            }
            user.getRoles().add(userRole.get());
            String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            myUserRepository.save(user);
        } else throw new UsernameNotFoundException("Role not found");


    }


    public Optional<MyUser> findById(long id) {

        Optional<MyUser> user = myUserRepository.findById(id);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Hibernate.initialize(user.get().getRoles());
        return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MyUser> findAll() {
        return myUserRepository.findAll();
    }
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(long id) {
        Optional<MyUser> user = findById(id);
        if (user.isPresent()) {
            myUserRepository.deleteById(id);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void update(MyUser user, long id) {
        Optional<MyUser> u = findById(id);
        u.ifPresent(x -> {
            x.setUsername(user.getUsername());
            x.setAge(user.getAge());
        });
    }
}
