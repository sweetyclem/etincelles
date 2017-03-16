package com.etincelles;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.etincelles.entities.Category;
import com.etincelles.entities.User;
import com.etincelles.entities.security.Role;
import com.etincelles.entities.security.UserRole;
import com.etincelles.service.CategoryService;
import com.etincelles.service.UserService;
import com.etincelles.utility.SecurityUtility;

@SpringBootApplication
public class EtincellesApplication implements CommandLineRunner {
    @Autowired
    private UserService     userService;

    @Autowired
    private CategoryService categoryService;

    public static void main( String[] args ) {
        SpringApplication.run( EtincellesApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {
        Category category = new Category();
        category.setName( "Etincelle" );
        categoryService.createCategory( category );

        Category cat2 = new Category();
        cat2.setName( "Mentor.e" );
        categoryService.createCategory( cat2 );

        Category cat3 = new Category();
        cat3.setName( "Coach" );
        categoryService.createCategory( cat3 );

        Category cat4 = new Category();
        cat4.setName( "Staff" );
        categoryService.createCategory( cat4 );

        User user1 = new User();
        user1.setFirstName( "Janet" );
        user1.setLastName( "Adams" );
        user1.setEmail( "janet@adams.com" );
        user1.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId( 1 );
        role1.setName( "ROLE_USER" );
        userRoles.add( new UserRole( user1, role1 ) );
        userService.createUser( user1, userRoles );
    }
}
