package com.etincelles.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.auth0.spring.security.mvc.Auth0Config;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
@Order( SecurityProperties.ACCESS_OVERRIDE_ORDER )
public class OAuthConfig extends Auth0Config {

    @Override
    protected void authorizeRequests( final HttpSecurity http ) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/css/**", "/fonts/**", "/js/**", "/login" ).permitAll()
                .antMatchers( "/portal/**" ).hasAuthority( "ROLE_ADMIN" )
                .antMatchers( securedRoute ).authenticated();
    }
}