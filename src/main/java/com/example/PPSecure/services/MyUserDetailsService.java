package com.example.PPSecure.services;

import com.example.PPSecure.model.MyUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MyUserDetailsService extends UserDetailsService {
    public void persist(MyUser user);
    @Query("")
    public MyUser findById(long id);
    public List<MyUser> findAll();
    public void deleteById(long id);
    public void update(MyUser user, long id);

}
