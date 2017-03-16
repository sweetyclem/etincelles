package com.etincelles.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.etincelles.entities.User;
import com.etincelles.service.UserService;
import com.etincelles.service.impl.UserSecurityService;
import com.etincelles.utility.MailConstructor;

@Controller
@RequestMapping( "/user" )
public class UserController {

    @Autowired
    private JavaMailSender      mailSender;

    @Autowired
    private MailConstructor     mailConstructor;

    @Autowired
    private UserService         userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping( value = "/update", method = RequestMethod.POST )
    public String addUserPost( @ModelAttribute( "user" ) User user, HttpServletRequest request ) {
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
}
