package com.springsecurityexercises.jwtsecurity.security;

import com.springsecurityexercises.jwtsecurity.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtUtils jwtUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (authentication instanceof JwtAuthenticationToken token) {
                String customerName = jwtUtils.extractCustomerName(token.jwt()); // sign check!
                if (customerName != null) {
                    UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(customerName);
                    if (userDetails.getUsername().equals(customerName)) {
                        return new UsernamePasswordAuthenticationToken(
                                customerName,
                                null,
                                userDetails.getAuthorities()
                        );
                    }
                } else {
                    throw new UsernameNotFoundException("Customer name not found");
                }
            } else {
                throw new AuthenticationServiceException("Wrong type of authentication");
            }
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("Token expired");
        } catch (SignatureException e) {
            throw new BadCredentialsException("Signature exception");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
