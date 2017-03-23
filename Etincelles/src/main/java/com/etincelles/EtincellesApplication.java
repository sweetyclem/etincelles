package com.etincelles;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.etincelles.entities.Message;
import com.etincelles.entities.User;
import com.etincelles.entities.security.Role;
import com.etincelles.entities.security.UserRole;
import com.etincelles.enumeration.Category;
import com.etincelles.enumeration.City;
import com.etincelles.enumeration.Type;
import com.etincelles.service.MessageService;
import com.etincelles.service.UserService;
import com.etincelles.utility.SecurityUtility;

@SpringBootApplication
public class EtincellesApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    MessageService      messageService;

    public static void main( String[] args ) {
        SpringApplication.run( EtincellesApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {

        Message message = new Message();
        message.setText(
                "Playing with balls of wool chase red laser dot for favor packaging over toy, yet have my breakfast spaghetti yarn. Peer out window, chatter at birds, lure them to mouth hunt by meowing loudly at 5am next to human slave food dispenser yet shove bum in owner's face like camera lens yet sweet beast, but annoy owner until he gives you food say meow repeatedly until belly rubs, feels good scratch leg; meow for can opener to feed me. Stare at the wall, play with food and get confused by dust rub face on everything, or licks paws for eat from dog's food so sun bathe chirp at birds. Find something else more interesting step on your keyboard while you're gaming and then turn in a circle eat prawns daintily with a claw then lick paws clean wash down prawns with a lap of carnation milk then retire to the warmest spot on the couch to claw at the fabric before taking a catnap, but flee in terror at cucumber discovered on floor and brown cats with pink ears leave fur on owners clothes and pelt around the house and up and down stairs chasing phantoms. Swat at dog eats owners hair then claws head and meow to be let in sleep nap hide head under blanket so no one can see and step on your keyboard while you're gaming and then turn in a circle and has closed eyes but still sees you. Lick sellotape brown cats with pink ears for purr for wack the mini furry mouse flop over stares at human while pushing stuff off a table. Lick yarn hanging out of own butt intrigued by the shower sit by the fire sweet beast cat is love, cat is life. " );
        message.setDate( new Date() );
        message.setTitle( "Cat Ipsum" );
        messageService.createMessage( message );

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
        user2.setDescription( "Clémentine est développeuse" );
        user2.setJob_title( "développeuse backend" );
        user2.setOrganization( "Greta" );
        user2.setPhone( "0761841201" );
        user2.setPromo( 9 );
        user2.setType( Type.Carrière );
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
        user4.setDescription( "Clémentine est développeuse" );
        user4.setJob_title( "développeuse" );
        user4.setOrganization( "Greta" );
        user4.setPhone( "0761841201" );
        user4.setPromo( 9 );
        user4.setType( Type.Carrière );
        user4.setFacebook( "anais.pirlotmares" );
        user4.setLinkedin( "clementine-pirlot" );
        user4.setTwitter( "sweetyclem" );
        Set<UserRole> user4Roles = new HashSet<>();
        user4Roles.add( new UserRole( user4, role2 ) );
        userService.createUser( user4, user4Roles );
    }
}
