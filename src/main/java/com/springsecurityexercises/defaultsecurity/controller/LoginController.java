package com.springsecurityexercises.defaultsecurity.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Метод на @PostMapping("/login"), в который приходили
 * данные с формы регистрации не нужен, так как реализуется
 * SpringSecurity
 * */

@Profile("def")
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login/logout";
    }
}