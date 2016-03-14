package com.matthewcasperson.elidetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class defines the security that will be applied to our REST API.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = Logger.getLogger(WebSecurityConfig.class.getName());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()               // disable CSRF because it isn't useful for an API
                .authorizeRequests()
                .anyRequest().authenticated()   // all endpoints require a login
                .and()
                .httpBasic();                   // we'll make use of Basic HTTP authentication
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
            For our purposes, we'll be using hard coded users. You wouldn't do this in production,
            but it means we don't have to rely on any external dependencies to get security
            up and running.
         */
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("user").password("user").roles("USER");
    }
}