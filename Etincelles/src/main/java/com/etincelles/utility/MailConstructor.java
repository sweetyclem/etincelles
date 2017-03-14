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

        String url = contextPath + "/newUser?token=" + token;
        String message = "\nVeuillez cliquer sur le lien pour créer votre compte. Votre mot de passe est : \n"
                + password;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo( user.getEmail() );
        email.setSubject( "Plateforme Etincelles- Créer un compte" );
        email.setText( url + message );
        email.setFrom( env.getProperty( "support.email" ) );
        return email;

    }
}
