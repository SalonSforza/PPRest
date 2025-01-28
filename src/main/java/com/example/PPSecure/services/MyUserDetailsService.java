package com.example.PPSecure.services;

import com.example.PPSecure.model.MyUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface MyUserDetailsService extends UserDetailsService {
    public void persist(MyUser user);
    public Optional<MyUser> findById(long id);
    public List<MyUser> findAll();
    public void deleteById(long id);
    public void update(MyUser user, long id);

}
