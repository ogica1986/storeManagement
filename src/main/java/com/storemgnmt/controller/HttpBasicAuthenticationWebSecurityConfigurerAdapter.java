package com.storemgnmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class HttpBasicAuthenticationWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private static final String CLIENT = "CLIENT";
    private static final String ADMIN = "ADMIN" ;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/storeMgmt/products/**").hasRole(CLIENT)
                .mvcMatchers(HttpMethod.GET, "/storeMgmt/products").hasRole(CLIENT)
                .mvcMatchers(HttpMethod.POST, "/storeMgmt/products").hasRole(ADMIN)
                .mvcMatchers(HttpMethod.PUT, "/storeMgmt/products/**").hasRole(ADMIN)
                .anyRequest().authenticated().and().httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("buyer")
                .password("{noop}buyerPass")
                .roles(CLIENT)
                .and()
                .withUser("admin")
                .password("{noop}adminPass")
                .roles(ADMIN,CLIENT);
    }

    }



