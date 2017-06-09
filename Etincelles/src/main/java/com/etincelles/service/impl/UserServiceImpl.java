package com.etincelles.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etincelles.entities.PasswordResetToken;
import com.etincelles.entities.User;
import com.etincelles.entities.security.UserRole;
import com.etincelles.repository.PasswordResetTokenRepository;
import com.etincelles.repository.RoleRepository;
import com.etincelles.repository.SkillRespository;
import com.etincelles.repository.UserRepository;
import com.etincelles.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final Logger          LOG = LoggerFactory.getLogger( UserService.class );

    @Autowired
    private UserRepository               userRepository;

    @Autowired
    private RoleRepository               roleRepository;

    @Autowired
    private SkillRespository             skillRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetToken( final String token ) {
        return passwordResetTokenRepository.findByToken( token );
    }

    @Override
    public void createPasswordResetTokenForUser( final User user, final String token ) {
        final PasswordResetToken myToken = new PasswordResetToken( token, user );
        passwordResetTokenRepository.save( myToken );
    }

    @Override
    public User findByEmail( String email ) {
        return userRepository.findByEmail( email );
    }

    @Override
    public User createUser( User user, Set<UserRole> userRoles ) {
        User localUser = userRepository.findByEmail( user.getEmail() );

        if ( localUser != null ) {
            LOG.info( "user {} already exists. Nothing will be done.", user.getEmail() );
        } else {
            for ( UserRole ur : userRoles ) {
                roleRepository.save( ur.getRole() );
            }

            user.getUserRoles().addAll( userRoles );

            localUser = userRepository.save( user );
        }

        return localUser;
    }

    @Override
    public User save( User user ) {
        return userRepository.save( user );
    }

    @Override
    public User findById( Long id ) {
        return userRepository.findOne( id );
    }

    @Override
    public Page<User> findAll( Pageable pageable ) {
        return userRepository.findAll( pageable );
    }

    @Override
    public Page<User> blurrySearch( String keyword, Pageable pageable ) {
        Page<User> keywordList = userRepository.findByKeywordAndSort( keyword, pageable );
        return keywordList;
    }

    @Override
    public List<String> getSectors() {
        return userRepository.getSectors();
    }
}
