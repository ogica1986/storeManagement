package com.storemgnmt.controller;

import com.storemgnmt.entities.StoreMgnmtAuthenticationType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class HttpBasicAuthenticationWebSecurityConfigurerAdapter {

    public static final String CLIENT = "CLIENT";
    public static final UserDetails BUYER = User.builder()
            .username("buyer")
            .password("{noop}buyerPass")
            .roles(CLIENT)
            .build();
    public static final String ADMIN = "ADMIN" ;
    public static final UserDetails ADMIN_DETAILS = User.builder()
            .username("admin")
            .password("{noop}adminPass")
            .roles(ADMIN, CLIENT)
            .build();

    public static final UserDetails GIT_DETAILS = User.builder()
            .username("ogica1986")
            .password("{noop}adminPass")
            .roles(ADMIN, CLIENT)
            .build();


    @Value("${com.storemgnmt.authentication}")
    private StoreMgnmtAuthenticationType authenticationType;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                );
        return Optional.ofNullable(authenticationType)
                .map(authenticationType-> {
                    try {
                        return authenticationType.applyAuthentication(httpSecurity);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).orElse(httpSecurity.httpBasic().and()).build();
    }

    @Bean
    public UserDetailsService inMemoryUsersService(){
        return new InMemoryUserDetailsManager(BUYER, ADMIN_DETAILS,GIT_DETAILS);
    }

}



