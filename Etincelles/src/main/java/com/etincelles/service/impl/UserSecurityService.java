package com.etincelles.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.etincelles.entities.User;
import com.etincelles.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        return null;

    }

    public UserDetails loadUserByEmail( String email ) throws Exception {
        User user = userRepository.findByEmail( email );

        if ( null == user ) {
            throw new Exception( "Email not found" );
        }

        return user;
    }
}