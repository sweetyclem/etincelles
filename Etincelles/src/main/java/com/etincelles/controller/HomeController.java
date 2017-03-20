package com.etincelles.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.etincelles.entities.PasswordResetToken;
import com.etincelles.entities.User;
import com.etincelles.entities.security.Role;
import com.etincelles.entities.security.UserRole;
import com.etincelles.enumeration.Category;
import com.etincelles.enumeration.Type;
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
        model.addAttribute( "coach", Category.COACH );
        model.addAttribute( "etincelle", Category.ETINCELLE );
        model.addAttribute( "staff", Category.STAFF );
        model.addAttribute( "mentor", Category.MENTOR );
        return "myAccount";
    }

    @RequestMapping( "/forgetPassword" )
    public String forgetPassword(
            HttpServletRequest request,
            @ModelAttribute( "email" ) String email,
            Model model ) {

        model.addAttribute( "classActiveForgetPassword", true );

        User user = userService.findByEmail( email );

        if ( user == null ) {
            model.addAttribute( "emailNotExist", true );
            return "myAccount";
        }

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode( password );
        user.setPassword( encryptedPassword );

        userService.save( user );

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser( user, token );

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail( appUrl, request.getLocale(), token, user,
                password );

        mailSender.send( newEmail );

        model.addAttribute( "forgetPasswordEmailSent", "true" );

        return "myAccount";
    }

    @RequestMapping( value = "/newUser", method = RequestMethod.POST )
    public String newUserPost(
            HttpServletRequest request,
            @ModelAttribute( "email" ) String email, @RequestParam( value = "category" ) Category category,
            Model model )
            throws Exception {
        model.addAttribute( "classActiveNewAccount", true );
        model.addAttribute( "email", email );

        if ( userService.findByEmail( email ) != null ) {
            model.addAttribute( "emailExists", true );

            return "myAccount";
        }

        User user = new User();
        user.setEmail( email );
        user.setCategory( category );

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

    @RequestMapping( "/updateUser" )
    public String newUser( Locale locale, @RequestParam( "token" ) String token, Model model ) {
        PasswordResetToken passToken = userService.getPasswordResetToken( token );

        if ( passToken == null ) {
            String message = "Invalid Token.";
            model.addAttribute( "message", message );
            return "redirect:/badRequest";
        }

        User user = passToken.getUser();
        String email = user.getEmail();
        UserDetails userDetails = userSecurityService.loadUserByUsername( email );

        Authentication authentication = new UsernamePasswordAuthenticationToken( userDetails, userDetails.getPassword(),
                userDetails.getAuthorities() );
        SecurityContextHolder.getContext().setAuthentication( authentication );

        model.addAttribute( "classActiveEdit", true );
        model.addAttribute( "user", user );
        return "myProfile";
    }

    @RequestMapping( value = "/updateUserInfo", method = RequestMethod.POST )
    public String updateUserPost( @ModelAttribute( "user" ) User user, HttpServletRequest request ) {
        userService.save( user );

        MultipartFile picture = user.getPicture();

        try {
            byte[] bytes = picture.getBytes();
            String name = user.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream( new File( "src/main/resources/static/images/user/" + name ) ) );
            stream.write( bytes );
            stream.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return "redirect:index";
    }

    @RequestMapping( "/directoryIndex" )
    public String directoryIndex( Model model ) {
        return "directoryIndex";
    }

    @RequestMapping( "/directory" )
    public String directory( Model model, @RequestParam( "type" ) String type ) {
        List<User> userList;
        switch ( type ) {
        case "careerParticipants":
            userList = userService.findByCategory( Category.ETINCELLE );
            List<User> careerList = new ArrayList<>();
            for ( User user : userList ) {
                if ( user.getType() == Type.CAREER ) {
                    careerList.add( user );
                }
            }
            model.addAttribute( "userList", careerList );
            break;
        case "startupParticipants":
            userList = userService.findByCategory( Category.ETINCELLE );
            List<User> startupList = new ArrayList<>();
            for ( User user : userList ) {
                if ( user.getType() == Type.STARTUP ) {
                    startupList.add( user );
                }
            }
            model.addAttribute( "userList", startupList );
            break;
        case "careerMentors":
            userList = userService.findByCategory( Category.MENTOR );
            List<User> careerMentorList = new ArrayList<>();
            for ( User user : userList ) {
                if ( user.getType() == Type.CAREER ) {
                    careerMentorList.add( user );
                }
            }
            model.addAttribute( "userList", careerMentorList );
            break;
        case "startupMentors":
            userList = userService.findByCategory( Category.MENTOR );
            List<User> startupMentorList = new ArrayList<>();
            for ( User user : userList ) {
                if ( user.getType() == Type.STARTUP ) {
                    startupMentorList.add( user );
                }
            }
            model.addAttribute( "userList", startupMentorList );
            break;

        case "staff":
            userList = userService.findByCategory( Category.STAFF );
            model.addAttribute( "userList", userList );
            break;
        case "coaches":
            userList = userService.findByCategory( Category.COACH );
            model.addAttribute( "userList", userList );
            break;

        default:
            break;
        }
        model.addAttribute( "career", Type.CAREER );
        model.addAttribute( "startup", Type.STARTUP );
        return "directory";
    }

    @RequestMapping( "/myProfile" )
    public String myProfile( Model model ) {
        // Get user from session

        return "myProfile";
    }

    @RequestMapping( "/calendar" )
    public String calendar( Model model ) {
        return "calendar";
    }

    @RequestMapping( "/faq" )
    public String faq( Model model ) {
        return "faq";
    }

    @RequestMapping( "/forum" )
    public String forum( Model model ) {
        return "forum";
    }

    @RequestMapping( "/news" )
    public String news( Model model ) {
        return "news";
    }
}
