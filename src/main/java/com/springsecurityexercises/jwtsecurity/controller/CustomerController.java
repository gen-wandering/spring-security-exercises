package com.springsecurityexercises.jwtsecurity.controller;

import com.springsecurityexercises.jwtsecurity.model.Customer;
import com.springsecurityexercises.jwtsecurity.service.CustomerService;
import com.springsecurityexercises.jwtsecurity.service.LoginService;
import com.springsecurityexercises.jwtsecurity.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

@Profile("jwt")
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final LoginService loginService;
    private final RegistrationService registrationService;

    @GetMapping("/check")
    public void check(@CurrentSecurityContext SecurityContext context) {
        System.out.println(context.getAuthentication());
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Customer>> getAllUsers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam(name = "name") String customerName) {
        return loginService.login(customerName);
    }

    @PostMapping("/reg")
    public ResponseEntity<String> registration(@RequestBody Customer customer) {
        return registrationService.register(customer);
    }
}