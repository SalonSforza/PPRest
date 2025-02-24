package com.example.PPSecure.services;

import com.example.PPSecure.model.MyUser;
import com.example.PPSecure.repositories.RoleRepository;
import com.example.PPSecure.repositories.UserRepository;
import com.example.PPSecure.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetails(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    @Transactional
    public void save(MyUser user) {
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

    }


    public MyUser findById(long id) {

        Optional<MyUser> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.get();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MyUser> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(long id) {
      Optional<MyUser> user = Optional.ofNullable(findById(id));
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void update(MyUser user, long id) {
        System.out.println(id);
        Optional <MyUser> u = userRepository.findById(id);

        System.out.println(u);
       if(u.isPresent()) {
           if(u.get().getRoles() == null) {
           u.get().setRoles(new HashSet<>());
       }
           u.get().setUsername(user.getUsername());
           u.get().setAge(user.getAge());
           u.get().setRoles(user.getRoles());
       } else {
           throw new UsernameNotFoundException("User not found");
       }




    }


    public MyUser findLoggedInUserByUserName() {
        org.springframework.security.core.userdetails.UserDetails userDetails = (org.springframework.security.core.userdetails.UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<MyUser> user = userRepository.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("User not found");
    }


}
