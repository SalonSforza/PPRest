package com.example.PPSecure.security;

import com.example.PPSecure.model.MyUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final MyUser myUser;

    public UserDetails(MyUser myUser) {
        this.myUser = myUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return myUser.getRoles();
    }

    @Override
    public String getPassword() {
        return this.myUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.myUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public MyUser getMyUser() {
        return myUser;
    }
}
