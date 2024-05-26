package com.springsecurityexercises.defaultsecurity.config;

import com.springsecurityexercises.filter.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * Filters
 * 1) Form : UsernamePasswordAuthenticationFilter
 * 2) Basic: BasicAuthenticationFilter,
 *           ForceEagerSessionCreationFilter, SessionManagementFilter - due to ".sessionManagement(...)"
 *
 * 3) Mutual: DisableEncodeUrlFilter, SecurityContextHolderFilter, CorsFilter, CsrfFilter,
 *            WebAsyncManagerIntegrationFilter, HeaderWriterFilter, LogoutFilter,
 *            SecurityContextHolderAwareRequestFilter, AnonymousAuthenticationFilter,
 *            AuthorizationFilter, ExceptionTranslationFilter, RequestCacheAwareFilter
 * */

@Profile("def")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // Roles

    @Bean
    public SecurityFilterChain form(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/hello").permitAll()
                        //.requestMatchers("role/admin/**").hasAuthority(ADMIN.getAuthority())
                        //.requestMatchers("role/moderator/**").hasAnyAuthority(ADMIN.getAuthority(), MODERATOR.getAuthority())
                        //.requestMatchers("role/user/**").hasAnyAuthority(ADMIN.getAuthority(), MODERATOR.getAuthority(), USER.getAuthority())
                        .anyRequest().authenticated())
                .addFilterBefore(new LoggingFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/hello")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/hello"))
                .build();
    }

    // Удаление Authentication

    /*@Bean
    public SecurityFilterChain basic(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/hello").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new LoggingFilter(), BasicAuthenticationFilter.class)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .logout(logout -> logout
                        .logoutSuccessUrl("/hello")
                        .deleteCookies("JSESSIONID"))
                .build();
    }*/
}