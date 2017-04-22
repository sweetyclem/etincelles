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

    public SimpleMailMessage constructResetPasswordEmail( Locale locale, User user, String password ) {

        String message = "Bonjour,\n\nVeuillez cliquer sur le lien pour accéder à votre compte.\n";
        String url = "https://etincelles.co/login";
        String mess2 = "\n\nVotre nouveau mot de passe est : \n" + password
                + "\n\nPour des raisons de sécurité, nous vous conseillons de changer ce mot de passe.\n\nCordialement,\n\nL'équipe Etincelles";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo( user.getEmail() );
        email.setSubject( "Plateforme Etincelles- Mot de passe oublié" );
        email.setText( message + url + mess2 );
        email.setFrom( env.getProperty( "support.email" ) );
        return email;

    }

    public SimpleMailMessage constructContactEmail( String name, String email, String text, String userEmail ) {

        SimpleMailMessage contactEmail = new SimpleMailMessage();
        contactEmail.setTo( userEmail );
        contactEmail.setSubject( "Un message vous a été envoyé " + "par " + name + " depuis le site Etincelles" );
        contactEmail.setText(
                "Pour répondre à ce message, merci de contacter la personne à cette adresse : " + email
                        + "\nContenu du message : \n\n" + text );
        contactEmail.setFrom( env.getProperty( "support.email" ) );
        return contactEmail;
    }
}
