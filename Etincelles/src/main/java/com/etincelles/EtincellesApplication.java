package com.etincelles;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.etincelles.entities.User;
import com.etincelles.entities.security.Role;
import com.etincelles.entities.security.UserRole;
import com.etincelles.enumeration.Category;
import com.etincelles.enumeration.Type;
import com.etincelles.service.UserService;
import com.etincelles.utility.SecurityUtility;

@SpringBootApplication
public class EtincellesApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public static void main( String[] args ) {
        SpringApplication.run( EtincellesApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {

        User user1 = new User();
        user1.setFirstName( "Janet" );
        user1.setLastName( "Adams" );
        user1.setEmail( "janet@adams.com" );
        user1.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user1.setCategory( Category.STAFF );
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId( 1 );
        role1.setName( "ROLE_USER" );
        userRoles.add( new UserRole( user1, role1 ) );
        userService.createUser( user1, userRoles );

        User user2 = new User();
        user2.setFirstName( "Clem" );
        user2.setLastName( "Pirlot" );
        user2.setEmail( "contact@clementinepirlot.fr" );
        user2.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user2.setCategory( Category.ETINCELLE );
        user2.setCity( "Lyon" );
        user2.setDescription( "Clémentine est développeuse" );
        user2.setJob_title( "développeuse backend" );
        user2.setOrganization( "Greta" );
        user2.setPhone( "0761841201" );
        user2.setPromo( 9 );
        user2.setType( Type.CAREER );
        user2.setFacebook( "anais.pirlotmares" );
        user2.setLinkedin( "clementine-pirlot" );
        user2.setTwitter( "sweetyclem" );
        Set<UserRole> user2Roles = new HashSet<>();
        Role role2 = new Role();
        role2.setRoleId( 1 );
        role2.setName( "ROLE_USER" );
        user2Roles.add( new UserRole( user2, role2 ) );
        userService.createUser( user2, user2Roles );
    }
}
