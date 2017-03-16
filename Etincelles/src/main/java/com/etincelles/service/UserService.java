package com.etincelles.service;

import java.util.List;
import java.util.Set;

import com.etincelles.entities.PasswordResetToken;
import com.etincelles.entities.User;
import com.etincelles.entities.security.UserRole;

public interface UserService {
    PasswordResetToken getPasswordResetToken( final String token );

    void createPasswordResetTokenForUser( final User user, final String token );

    User findByEmail( String email );

    User createUser( User user, Set<UserRole> userRoles ) throws Exception;

    User save( User user );

    List<User> findAll();
}
