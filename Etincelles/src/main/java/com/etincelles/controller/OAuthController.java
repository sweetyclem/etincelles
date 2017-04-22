package com.etincelles.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.auth0.spring.security.mvc.Auth0CallbackHandler;

@Controller
public class OAuthController extends Auth0CallbackHandler {

    @RequestMapping( value = "${auth0.loginCallback}", method = RequestMethod.GET )
    protected void callback( final HttpServletRequest req, final HttpServletResponse res )
            throws ServletException, IOException {
        super.handle( req, res );
    }
}