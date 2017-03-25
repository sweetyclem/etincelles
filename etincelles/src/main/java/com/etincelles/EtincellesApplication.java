package com.etincelles;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.etincelles.entities.User;
import com.etincelles.entities.security.Role;
import com.etincelles.entities.security.UserRole;
import com.etincelles.enumeration.Category;
import com.etincelles.enumeration.City;
import com.etincelles.enumeration.Type;
import com.etincelles.service.UserService;
import com.etincelles.utility.SecurityUtility;

@SpringBootApplication
public class EtincellesApplication extends SpringBootServletInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
        return application.sources( EtincellesApplication.class );
    }

    public static void main( String[] args ) {
        SpringApplication.run( EtincellesApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {

        User user1 = new User();
        user1.setFirstName( "Audrey" );
        user1.setLastName( "Abitan" );
        user1.setEmail( "janet@adams.com" );
        user1.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user1.setCategory( Category.Staff );
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId( 1 );
        role1.setName( "ROLE_USER" );
        userRoles.add( new UserRole( user1, role1 ) );
        userService.createUser( user1, userRoles );

        User user2 = new User();
        user2.setFirstName( "Stéphanie" );
        user2.setLastName( "Savin" );
        user2.setEmail( "contact@clementinepirlot.fr" );
        user2.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user2.setCategory( Category.Mentore );
        user2.setCity( City.Lyon );
        user2.setDescription( "Stéphanie est mentore depuis le début" );
        user2.setJob_title( "Responsable communication" );
        user2.setOrganization( "Econocom" );
        user2.setPhone( "0761841201" );
        user2.setPromo( 9 );
        user2.setType( Type.Carrière );
        user2.setLinkedin( "stephanie-savin" );
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
        user3.setCategory( Category.Etincelle );
        user3.setCity( City.Paris );
        user3.setDescription( "Clémentine est développeuse" );
        user3.setJob_title( "développeuse" );
        user3.setOrganization( "Greta" );
        user3.setPhone( "0761841201" );
        user3.setPromo( 9 );
        user3.setType( Type.Carrière );
        user3.setFacebook( "anais.pirlotmares" );
        user3.setLinkedin( "clementine-pirlot" );
        user3.setTwitter( "sweetyclem" );
        Set<UserRole> user3Roles = new HashSet<>();
        user3Roles.add( new UserRole( user3, role2 ) );
        userService.createUser( user3, user3Roles );

        User user4 = new User();
        user4.setFirstName( "Stéphanie" );
        user4.setLastName( "Herr" );
        user4.setEmail( "sweetyclem+test@gmail.com" );
        user4.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user4.setCategory( Category.Etincelle );
        user4.setCity( City.Lyon );
        user4.setDescription( "Stéphanie s'occupe de la communication" );
        user4.setJob_title( "Responsable communication" );
        user4.setOrganization( "Social Builder" );
        user4.setPhone( "0761841201" );
        user4.setPromo( 9 );
        user4.setType( Type.Carrière );
        user4.setLinkedin( "clementine-pirlot" );
        Set<UserRole> user4Roles = new HashSet<>();
        user4Roles.add( new UserRole( user4, role2 ) );
        userService.createUser( user4, user4Roles );
    }
}
