package com.springsecurityexercises.jwtsecurity.service;

import com.springsecurityexercises.jwtsecurity.utils.JwtUtils;
import com.springsecurityexercises.jwtsecurity.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final CustomerService customerService;
    private final JwtUtils jwtUtils;

    public ResponseEntity<String> register(Customer customer) {
        if (customerService.getCustomer(customer.getName()) == null) {
            customerService.saveCustomer(customer);
            String jwt = jwtUtils.generateToken(customer);
            return ResponseEntity.ok(jwt);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer is already registered");
    }
}
