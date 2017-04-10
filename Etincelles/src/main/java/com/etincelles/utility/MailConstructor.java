package com.etincelles.utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.etincelles.entities.User;

@Component
public class MailConstructor {
    @Autowired
    private Environment env;

    public SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user, String password ) {

        String url = contextPath + "/updateUser?token=" + token;
        String message = "Bonjour\nVeuillez cliquer sur le lien pour créer votre compte.\n";
        String mess2 = "\nVotre mot de passe actuel est : \n" + password
                + "\nPour des raisons de sécurité, vous devez changer ce mot de passe";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo( user.getEmail() );
        email.setSubject( "Plateforme Etincelles- Créer un compte" );
        email.setText( message + url + mess2 );
        email.setFrom( env.getProperty( "support.email" ) );
        return email;

    }

    public SimpleMailMessage constructContactEmail( String name, String email, String text, String userEmail ) {

        SimpleMailMessage contactEmail = new SimpleMailMessage();
        contactEmail.setTo( userEmail );
        contactEmail.setSubject( "Un message vous a été envoyé " + "par " + name + " depuis le site Etincelles" );
        contactEmail.setText( "Ne pas répondre directement,répondez à cette adresse : " + email + "\n" + text );
        contactEmail.setFrom( env.getProperty( "support.email" ) );
        contactEmail.setText( text );
        contactEmail.setFrom( email );
        return contactEmail;
    }
}
