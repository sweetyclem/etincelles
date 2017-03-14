package com.etincelles.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.etincelles.entities.User;
import com.etincelles.repository.UserRepository;

public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByEmail( String email ) throws Exception {
        User user = userRepository.findByEmail( email );

        if ( null == user ) {
            throw new Exception( "Email not found" );
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername( String arg0 ) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
}
