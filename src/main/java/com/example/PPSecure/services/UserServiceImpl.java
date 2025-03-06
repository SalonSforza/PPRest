package com.example.PPSecure.services;

import com.example.PPSecure.DTO.UserDTOtoReceive;
import com.example.PPSecure.DTO.UserDTOtoSend;
import com.example.PPSecure.model.User;
import com.example.PPSecure.repositories.RoleRepository;
import com.example.PPSecure.repositories.UserRepository;
import com.example.PPSecure.security.UserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetails(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    @Transactional
    public void save(User user) {
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

    }


    public User findById(long id) {

        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDTOtoSend> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List <User> users = userRepository.findAll();
        List<UserDTOtoSend> userDTOtoSend = new ArrayList<>();
        for (User user : users) {
          userDTOtoSend.add(modelMapper.map(user, UserDTOtoSend.class));
        }
        return userDTOtoSend;
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(long id) {
      Optional<User> user = Optional.ofNullable(findById(id));
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void update(User user, long id) {
        System.out.println(id);
        Optional <User> u = userRepository.findById(id);

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


    public User findLoggedInUserByUserName() {
        org.springframework.security.core.userdetails.UserDetails userDetails =
                (org.springframework.security.core.userdetails.UserDetails) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
