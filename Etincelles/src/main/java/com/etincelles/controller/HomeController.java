package com.etincelles.controller;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.etincelles.entities.PasswordResetToken;
import com.etincelles.entities.User;
import com.etincelles.entities.security.Role;
import com.etincelles.entities.security.UserRole;
import com.etincelles.service.UserService;
import com.etincelles.service.impl.UserSecurityService;
import com.etincelles.utility.MailConstructor;
import com.etincelles.utility.SecurityUtility;

@Controller
public class HomeController {
    @Autowired
    private JavaMailSender      mailSender;

    @Autowired
    private MailConstructor     mailConstructor;

    @Autowired
    private UserService         userService;

    @Autowired
    private UserSecurityService userSecurityService;

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

    @RequestMapping( value = "/newUser", method = RequestMethod.POST )
    public String newUserPost(
            HttpServletRequest request,
            @ModelAttribute( "email" ) String email,
            Model model ) throws Exception {
        model.addAttribute( "classActiveNewAccount", true );
        model.addAttribute( "email", email );

        if ( userService.findByEmail( email ) != null ) {
            model.addAttribute( "emailExists", true );

            return "myAccount";
        }

        User user = new User();
        user.setEmail( email );

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode( password );
        user.setPassword( encryptedPassword );

        Role role = new Role();
        role.setRoleId( 1 );
        role.setName( "ROLE_USER" );
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add( new UserRole( user, role ) );
        userService.createUser( user, userRoles );

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser( user, token );

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        SimpleMailMessage mail = mailConstructor.constructResetTokenEmail( appUrl, request.getLocale(), token, user,
                password );

        mailSender.send( mail );

        model.addAttribute( "emailSent", "true" );

        return "myAccount";
    }

    @RequestMapping( "/newUser" )
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
