package com.springsecurityexercises.model;

import org.springframework.security.core.GrantedAuthority;


public enum Roles implements GrantedAuthority {
    ADMIN, MODERATOR, USER;

    @Override
    public String getAuthority() {
        return toString();
    }
}