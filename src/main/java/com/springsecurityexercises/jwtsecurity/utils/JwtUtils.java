package com.springsecurityexercises.jwtsecurity.utils;

import com.springsecurityexercises.jwtsecurity.model.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${app.security.secret}")
    private String SECRET_KEY;

    private final Duration EXPIRATION_TIME = Duration.ofMinutes(2);

    private SecretKey getSingingKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractCustomerName(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private <R> R getClaim(String token, Function<Claims, R> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSingingKey()) // !
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(Customer customer) {
        return generateToken(new HashMap<>(), customer);
    }

    public String generateToken(Map<String, Object> extraClaims, Customer customer) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(customer.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME.toMillis()))
                .signWith(getSingingKey(), Jwts.SIG.HS256)
                .compact();
    }
}
