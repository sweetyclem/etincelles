package com.etincelles.service;

import java.util.List;
import java.util.Set;

import com.etincelles.entities.PasswordResetToken;
import com.etincelles.entities.User;
import com.etincelles.entities.UserSkill;
import com.etincelles.entities.security.UserRole;
import com.etincelles.enumeration.Category;
import com.etincelles.enumeration.City;

public interface UserService {
    PasswordResetToken getPasswordResetToken( final String token );

    void createPasswordResetTokenForUser( final User user, final String token );

    User findByEmail( String email );

    User findById( Long id );

    User createUser( User user, Set<UserRole> userRoles, Set<UserSkill> userSkills ) throws Exception;

    User save( User user );

    List<User> findAll();

    List<User> findByCategory( Category category );

    List<User> findByCity( City city );

    public List<User> blurrySearch( String name );
}
