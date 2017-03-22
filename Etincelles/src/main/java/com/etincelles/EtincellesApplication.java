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

        User user3 = new User();
        user3.setFirstName( "Clémentine" );
        user3.setLastName( "Pirlot" );
        user3.setEmail( "sweetyclem+test@gmail.com" );
        user3.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user3.setCategory( Category.ETINCELLE );
        user3.setCity( "Lyon" );
        user3.setDescription( "Clémentine est développeuse" );
        user3.setJob_title( "développeuse" );
        user3.setOrganization( "Greta" );
        user3.setPhone( "0761841201" );
        user3.setPromo( 9 );
        user3.setType( Type.CAREER );
        user3.setFacebook( "anais.pirlotmares" );
        user3.setLinkedin( "clementine-pirlot" );
        user3.setTwitter( "sweetyclem" );
        Set<UserRole> user3Roles = new HashSet<>();
        user3Roles.add( new UserRole( user3, role2 ) );
        userService.createUser( user3, user3Roles );

        User user4 = new User();
        user4.setFirstName( "Clémentine" );
        user4.setLastName( "Pirlot" );
        user4.setEmail( "sweetyclem+test@gmail.com" );
        user4.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user4.setCategory( Category.ETINCELLE );
        user4.setCity( "Lyon" );
        user4.setDescription( "Clémentine est développeuse" );
        user4.setJob_title( "développeuse" );
        user4.setOrganization( "Greta" );
        user4.setPhone( "0761841201" );
        user4.setPromo( 9 );
        user4.setType( Type.CAREER );
        user4.setFacebook( "anais.pirlotmares" );
        user4.setLinkedin( "clementine-pirlot" );
        user4.setTwitter( "sweetyclem" );
        Set<UserRole> user4Roles = new HashSet<>();
        user4Roles.add( new UserRole( user4, role2 ) );
        userService.createUser( user4, user4Roles );
    }
}
