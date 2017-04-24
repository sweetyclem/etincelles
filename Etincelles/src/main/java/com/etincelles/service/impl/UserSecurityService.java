package com.etincelles.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etincelles.entities.User;
import com.etincelles.repository.UserRepository;

@Transactional
@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        User user = userRepository.findByEmail( username );

        if ( null == user ) {
            throw new UsernameNotFoundException( "Email not found" );
        }

        return user;
    }

    public UserDetails loadUserByEmail( String email ) throws UsernameNotFoundException {
        User user = userRepository.findByEmail( email );

        if ( null == user ) {
            throw new UsernameNotFoundException( "Email not found" );
        }

        return user;
    }
}