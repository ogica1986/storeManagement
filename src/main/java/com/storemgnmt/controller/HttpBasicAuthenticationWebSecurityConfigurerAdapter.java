package com.storemgnmt.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpBasicAuthenticationWebSecurityConfigurerAdapter {

    public static final String CLIENT = "CLIENT";
    public static final String ADMIN = "ADMIN" ;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public UserDetailsService users(){
        UserDetails user = User.builder()
                .username("buyer")
                .password("{noop}buyerPass")
                .roles(CLIENT)
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}adminPass")
                .roles(ADMIN,CLIENT)
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}



