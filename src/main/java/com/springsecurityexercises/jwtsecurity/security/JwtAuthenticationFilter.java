package com.springsecurityexercises.jwtsecurity.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtAuthenticationManager jwtAuthenticationManager;

    public JwtAuthenticationFilter(JwtAuthenticationManager jwtAuthenticationManager) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();

        // registration request
        if (servletPath.matches("^/reg/?$") || servletPath.matches("^/login/?$")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Get JWT token
        String jwtHeader = request.getHeader("Authorization");

        if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
            String jwt = jwtHeader.substring(7);
            JwtAuthenticationToken token = new JwtAuthenticationToken(jwt);
            try {
                Authentication authToken = jwtAuthenticationManager.authenticate(token);
                if (authToken == null) throw new AuthenticationServiceException("Authentication failed");
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (AuthenticationException e) {
                log.warn(e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
            filterChain.doFilter(request, response);
        } else
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No token found");
    }
}