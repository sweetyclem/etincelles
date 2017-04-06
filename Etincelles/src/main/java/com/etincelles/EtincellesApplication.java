package com.etincelles;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.etincelles.entities.Skill;
import com.etincelles.entities.User;
import com.etincelles.entities.UserSkill;
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

        Skill skill = new Skill();
        skill.setName( "Design Thinking" );
        Skill skill2 = new Skill();
        skill2.setName( "DevOps" );
        Skill skill3 = new Skill();
        skill3.setName( "Lean Startup" );
        Skill skill4 = new Skill();
        skill4.setName( "Méthode Agile" );

        User user1 = new User();
        user1.setFirstName( "Audrey" );
        user1.setLastName( "Abitan" );
        user1.setEmail( "staffetincelles@gmail.com" );
        user1.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user1.setCategory( Category.Equipe );
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId( 1 );
        role1.setName( "ROLE_USER" );
        userRoles.add( new UserRole( user1, role1 ) );
        Set<UserSkill> userSkills = new HashSet<>();
        userSkills.add( new UserSkill( user1, skill4 ) );
        userSkills.add( new UserSkill( user1, skill ) );
        userService.createUser( user1, userRoles, userSkills );

        User user2 = new User();
        user2.setFirstName( "Stéphanie" );
        user2.setLastName( "Savin" );
        user2.setEmail( "contact@clementinepirlot.fr" );
        user2.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user2.setCategory( Category.Mentore );
        user2.setCity( City.Lyon );
        user2.setDescription( "Stéphanie est mentore depuis le début" );
        user2.setPromo( 9 );
        user2.setType( Type.Carrière );
        user2.setLinkedin( "stephanie-savin" );
        Set<UserRole> user2Roles = new HashSet<>();
        Role role2 = new Role();
        role2.setRoleId( 1 );
        role2.setName( "ROLE_USER" );
        user2Roles.add( new UserRole( user2, role2 ) );
        Set<UserSkill> userSkills2 = new HashSet<>();
        userSkills2.add( new UserSkill( user2, skill2 ) );
        userSkills2.add( new UserSkill( user2, skill3 ) );
        userService.createUser( user2, user2Roles, userSkills2 );

        User user3 = new User();
        user3.setFirstName( "Clémentine" );
        user3.setLastName( "Pirlot" );
        user3.setEmail( "sweetyclem+test@gmail.com" );
        user3.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user3.setCategory( Category.Etincelle );
        user3.setCity( City.Paris );
        user3.setDescription( "Clémentine est développeuse" );
        user3.setPromo( 22 );
        user3.setType( Type.CarrièreData );
        user3.setFacebook( "anais.pirlotmares" );
        user3.setLinkedin( "clementine-pirlot" );
        user3.setTwitter( "sweetyclem" );
        Set<UserRole> user3Roles = new HashSet<>();
        user3Roles.add( new UserRole( user3, role2 ) );
        Set<UserSkill> userSkills3 = new HashSet<>();
        userSkills3.add( new UserSkill( user3, skill2 ) );
        userSkills3.add( new UserSkill( user3, skill ) );
        userService.createUser( user3, user3Roles, userSkills3 );

        User user4 = new User();
        user4.setFirstName( "Stéphanie" );
        user4.setLastName( "Herr" );
        user4.setEmail( "sweetyclem+test2@gmail.com" );
        user4.setPassword( SecurityUtility.passwordEncoder().encode( "p" ) );
        user4.setCategory( Category.Etincelle );
        user4.setCity( City.Lyon );
        user4.setDescription( "Stéphanie s'occupe de la communication" );
        user4.setPromo( 9 );
        user4.setType( Type.Carrière );
        user4.setLinkedin( "clementine-pirlot" );
        Set<UserRole> user4Roles = new HashSet<>();
        user4Roles.add( new UserRole( user4, role2 ) );
        Set<UserSkill> userSkills4 = new HashSet<>();
        userSkills4.add( new UserSkill( user4, skill ) );
        userSkills4.add( new UserSkill( user4, skill3 ) );
        userService.createUser( user4, user4Roles, userSkills4 );
    }
}
