package com.storemgnmt.entities;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public enum StoreMgnmtAuthenticationType {
    BASIC,
    OAUTH2 {
        @Override
        public HttpSecurity applyAuthentication(HttpSecurity httpSecurity) throws Exception{
            return httpSecurity.oauth2Login()..and();
        }
    };

    public HttpSecurity applyAuthentication(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic().and();
    }
}
