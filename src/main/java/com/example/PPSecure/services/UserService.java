package com.example.PPSecure.services;

import com.example.PPSecure.model.MyUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(MyUser user);

    MyUser findById(long id);
   List<MyUser> findAll();
     void deleteById(long id);
     void update(MyUser user, long id);

}
