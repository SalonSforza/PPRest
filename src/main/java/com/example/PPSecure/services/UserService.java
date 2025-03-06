package com.example.PPSecure.services;

import com.example.PPSecure.DTO.UserDTOtoSend;
import com.example.PPSecure.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(User user);

    User findById(long id);
   List<UserDTOtoSend> findAll();
     void deleteById(long id);
     void update(User user, long id);

}
