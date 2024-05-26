package com.springsecurityexercises.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    @GetMapping("/user")
    public String user(Model model,
                       @CurrentSecurityContext SecurityContext securityContext,
                       @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("role", "USER");
        return "roles/role-access";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @GetMapping("/moderator")
    public String moderator(Model model,
                            @CurrentSecurityContext SecurityContext securityContext,
                            @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("role", "MODERATOR");
        return "roles/role-access";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String admin(Model model,
                        @CurrentSecurityContext SecurityContext securityContext,
                        @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("role", "ADMIN");
        return "roles/role-access";
    }
}
