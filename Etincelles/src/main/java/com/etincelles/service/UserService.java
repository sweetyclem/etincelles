package com.etincelles.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.etincelles.entities.PasswordResetToken;
import com.etincelles.entities.User;
import com.etincelles.entities.security.UserRole;

public interface UserService {
    PasswordResetToken getPasswordResetToken( final String token );

    void createPasswordResetTokenForUser( final User user, final String token );

    User findByEmail( String email );

    User findById( Long id );

    User createUser( User user, Set<UserRole> userRoles ) throws Exception;

    User save( User user );

    Page<User> findAll( Pageable pageable );

    Page<User> blurrySearch( String keyword, Pageable pageable );
}
