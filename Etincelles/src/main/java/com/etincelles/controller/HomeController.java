package com.etincelles.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etincelles.entities.PasswordResetToken;
import com.etincelles.entities.User;
import com.etincelles.service.UserService;
import com.etincelles.service.impl.UserSecurityService;

@Controller
public class HomeController {
    @Autowired
    UserService         userService;

    @Autowired
    UserSecurityService userSecurityService;

    @RequestMapping( "/" )
    public String index() {
        return "index";
    }

    @RequestMapping( "/login" )
    public String login( Model model ) {
        model.addAttribute( "classActiveLogin", true );
        return "myAccount";
    }

    @RequestMapping( "/forgetPassword" )
    public String forgetPassword( Model model ) {
        return "myAccount";
    }

    @RequestMapping( "/newEmail" )
    public String newUser( Locale locale, @RequestParam( "token" ) String token, Model model ) {
        PasswordResetToken passToken = userService.getPasswordResetToken( token );

        if ( passToken == null ) {
            String message = "Invalid Token.";
            model.addAttribute( "message", message );
            return "redirect:/badRequest";
        }

        User user = passToken.getUser();
        String email = user.getEmail();
        UserDetails userDetails = userSecurityService.loadUserByEmail( email );

        Authentication authentication = new UsernamePasswordAuthenticationToken( userDetails, userDetails.getPassword(),
                userDetails.getAuthorities() );
        SecurityContextHolder.getContext().setAuthentication( authentication );

        model.addAttribute( "classActiveEdit", true );
        return "myProfile";
    }
}
