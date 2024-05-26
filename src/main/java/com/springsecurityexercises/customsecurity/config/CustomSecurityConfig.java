package com.springsecurityexercises.customsecurity.config;

import com.springsecurityexercises.customsecurity.security.CustomAuthenticationFilter;
import com.springsecurityexercises.customsecurity.security.CustomAuthenticationManager;
import com.springsecurityexercises.customsecurity.security.CustomAuthenticationProvider;
import com.springsecurityexercises.filter.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Profile("custom")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class CustomSecurityConfig {

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public CustomAuthenticationManager customAuthenticationManager() {
        return new CustomAuthenticationManager(customAuthenticationProvider());
    }

    @Bean
    public SecurityFilterChain custom(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/hellos/**", "/role/**")
                .addFilterAt(new CustomAuthenticationFilter(customAuthenticationManager()), BasicAuthenticationFilter.class)
                .addFilterBefore(new LoggingFilter(), CustomAuthenticationFilter.class)
                .build();
    }
}