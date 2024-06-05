package com.springsecurityexercises.jwtsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    @GetMapping("/user")
    public String user() {
        return "Role User";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @GetMapping("/moderator")
    public String moderator() {
        return "Role Moderator";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Role Admin";
    }
}
