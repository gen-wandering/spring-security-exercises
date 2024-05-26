package com.springsecurityexercises.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(
            // Possible to get access to Authentication
            Authentication authentication
    ) {
        return "hello/hello";
    }

    @GetMapping(value = "/hellos")
    public String hellos(
            // Possible to get access to Authentication and Session
            Authentication authentication,
            HttpServletRequest request,
            @CurrentSecurityContext SecurityContext securityContext
    ) {
        System.out.println(authentication);
        System.out.println(securityContext.getAuthentication());

        return "hello/secure-hello";
    }
}