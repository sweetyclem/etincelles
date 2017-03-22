package com.etincelles.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.etincelles.entities.PasswordResetToken;
import com.etincelles.entities.User;
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

    @RequestMapping( "/updateUser" )
    public String newUser( Locale locale, @RequestParam( "token" ) String token, Model model ) {
        PasswordResetToken passToken = userService.getPasswordResetToken( token );

        if ( passToken == null ) {
            String message = "Invalid Token.";
            model.addAttribute( "message", message );
            return "redirect:/badRequestPage";
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

    @RequestMapping( "/updateUserInfo" )
    public String updateGet( Model model, Principal principal ) {
        User activeUser = (User) ( (Authentication) principal ).getPrincipal();
        User user = userService.findByEmail( activeUser.getEmail() );
        model.addAttribute( "user", user );
        model.addAttribute( "classActiveEdit", true );
        return "myProfile";
    }

    @RequestMapping( value = "/updateUserInfo", method = RequestMethod.POST )
    public String updateUserInfo( @ModelAttribute( "user" ) User user, HttpServletRequest request,
            @ModelAttribute( "newPassword" ) String newPassword, Model model ) throws Exception {

        User currentUser = userService.findById( user.getId() );
        if ( currentUser == null ) {
            throw new Exception( "User not found" );
        }

        /* check email already exists */
        if ( userService.findByEmail( user.getEmail() ) != null ) {
            if ( userService.findByEmail( user.getEmail() ).getId() != currentUser.getId() ) {
                model.addAttribute( "emailExists", true );
                return "myProfile";
            }
        }

        MultipartFile picture = user.getPicture();
        if ( !( picture.isEmpty() ) ) {
            try {
                byte[] bytes = picture.getBytes();
                String name = user.getId() + ".png";
                if ( Files.exists( Paths.get( "src/main/resources/static/images/user/" + name ) ) ) {
                    Files.delete( Paths.get( "src/main/resources/static/images/user/" + name ) );
                }
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream( new File( "src/main/resources/static/images/user/" + name ) ) );
                stream.write( bytes );
                stream.close();
                currentUser.setHasPicture( true );
            } catch ( Exception e ) {
                System.out.println( "Erreur ligne 152" );
                e.printStackTrace();
            }
        }

        BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
        String dbPassword = currentUser.getPassword();

        // verify current password
        if ( !( passwordEncoder.matches( user.getPassword(), dbPassword ) ) ) {
            model.addAttribute( "incorrectPassword", true );
            return "myProfile";
        }

        // update password
        if ( newPassword != null && !newPassword.isEmpty() && !newPassword.equals( "" ) ) {
            if ( passwordEncoder.matches( user.getPassword(), dbPassword ) ) {
                currentUser.setPassword( passwordEncoder.encode( newPassword ) );
            } else {
                model.addAttribute( "incorrectPassword", true );
                return "myProfile";
            }
        }

        currentUser.setFirstName( user.getFirstName() );
        currentUser.setLastName( user.getLastName() );
        currentUser.setEmail( user.getEmail() );
        currentUser.setCategory( user.getCategory() );
        currentUser.setCity( user.getCity() );
        currentUser.setDescription( user.getDescription() );
        currentUser.setFacebook( user.getFacebook() );
        currentUser.setTwitter( user.getTwitter() );
        currentUser.setLinkedin( user.getLinkedin() );
        currentUser.setPhone( user.getPhone() );
        currentUser.setPromo( user.getPromo() );
        currentUser.setType( user.getType() );
        currentUser.setOrganization( user.getOrganization() );
        currentUser.setJob_title( user.getJob_title() );

        userService.save( currentUser );

        model.addAttribute( "updateSuccess", true );
        model.addAttribute( "user", currentUser );
        model.addAttribute( "classActiveEdit", true );

        UserDetails userDetails = userSecurityService.loadUserByUsername( currentUser.getEmail() );

        Authentication authentication = new UsernamePasswordAuthenticationToken( userDetails, userDetails.getPassword(),
                userDetails.getAuthorities() );

        SecurityContextHolder.getContext().setAuthentication( authentication );

        return "myProfile";

    }

    @RequestMapping( "/directoryIndex" )
    public String directoryIndex( Model model ) {
        List<User> userList;
        userList = userService.findAll();
        List<User> users = new ArrayList<>();
        for ( User user : userList ) {
            if ( user.getEnabled() ) {
                users.add( user );
            }
        }
        model.addAttribute( "userList", users );
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
                if ( user.getType() == Type.CAREER && user.getEnabled() ) {
                    careerList.add( user );
                }
            }
            model.addAttribute( "userList", careerList );
            break;
        case "startupParticipants":
            userList = userService.findByCategory( Category.ETINCELLE );
            List<User> startupList = new ArrayList<>();
            for ( User user : userList ) {
                if ( user.getType() == Type.STARTUP && user.getEnabled() ) {
                    startupList.add( user );
                }
            }
            model.addAttribute( "userList", startupList );
            break;
        case "careerMentors":
            userList = userService.findByCategory( Category.MENTOR );
            List<User> careerMentorList = new ArrayList<>();
            for ( User user : userList ) {
                if ( user.getType() == Type.CAREER && user.getEnabled() ) {
                    careerMentorList.add( user );
                }
            }
            model.addAttribute( "userList", careerMentorList );
            break;
        case "startupMentors":
            userList = userService.findByCategory( Category.MENTOR );
            List<User> startupMentorList = new ArrayList<>();
            for ( User user : userList ) {
                if ( user.getType() == Type.STARTUP && user.getEnabled() ) {
                    startupMentorList.add( user );
                }
            }
            model.addAttribute( "userList", startupMentorList );
            break;

        case "staff":
            userList = userService.findByCategory( Category.STAFF );
            List<User> users = new ArrayList<>();
            for ( User user : userList ) {
                if ( user.getEnabled() ) {
                    users.add( user );
                }
            }
            model.addAttribute( "userList", users );
            break;
        case "coaches":
            userList = userService.findByCategory( Category.COACH );
            users = new ArrayList<>();
            for ( User user : userList ) {
                if ( user.getEnabled() ) {
                    users.add( user );
                }
            }
            model.addAttribute( "userList", users );
            break;

        default:
            break;
        }
        model.addAttribute( "career", Type.CAREER );
        model.addAttribute( "startup", Type.STARTUP );
        return "directory";
    }

    @RequestMapping( "/userDetail" )
    public String UserDetail( @RequestParam( "id" ) Long id, Model model ) {
        User user = userService.findById( id );
        model.addAttribute( "user", user );
        return "userDetail";
    }

    @RequestMapping( "/myProfile" )
    public String myProfile( Model model, Principal principal ) {
        User activeUser = (User) ( (Authentication) principal ).getPrincipal();
        User user = userService.findByEmail( activeUser.getEmail() );
        model.addAttribute( "user", user );
        model.addAttribute( "classActiveEdit", true );
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
