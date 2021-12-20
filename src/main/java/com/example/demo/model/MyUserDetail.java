package com.example.demo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetail implements UserDetails {

    private static final Logger LOGGER= LoggerFactory.getLogger(MyUserDetail.class);

    public User user;

    public MyUserDetail(User user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        long lastLoginAttempt=user.getLastLoginAttempt().getTime();
        long timeNow=System.currentTimeMillis();
        if(user.getFailedLoginAttempts() < 5)
            return true;
        else{
            if (timeNow - lastLoginAttempt > 300000)
                LOGGER.info("Account {} is locked!");
            return timeNow - lastLoginAttempt > 300000;
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
