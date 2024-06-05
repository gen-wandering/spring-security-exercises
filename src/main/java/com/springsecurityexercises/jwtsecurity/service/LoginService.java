package com.springsecurityexercises.jwtsecurity.service;

import com.springsecurityexercises.jwtsecurity.utils.JwtUtils;
import com.springsecurityexercises.jwtsecurity.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final CustomerService customerService;
    private final JwtUtils jwtUtils;

    public ResponseEntity<String> login(String customerName) {
        Customer customer = customerService.getCustomer(customerName);
        if (customer != null) {
            String jwt = jwtUtils.generateToken(customer);
            return ResponseEntity.ok(jwt);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }
}
