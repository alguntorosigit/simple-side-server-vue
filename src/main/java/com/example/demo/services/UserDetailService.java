package com.example.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {

    UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException;
}