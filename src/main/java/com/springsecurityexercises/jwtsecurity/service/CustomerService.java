package com.springsecurityexercises.jwtsecurity.service;

import com.springsecurityexercises.jwtsecurity.model.Customer;
import com.springsecurityexercises.jwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final UserRepository userRepository;

    public Iterable<Customer> getAllCustomers() {
        return userRepository.findAll();
    }

    public Customer getCustomer(String name) {
        return userRepository.findByName(name);
    }

    public void saveCustomer(Customer customer) {
        userRepository.save(customer);
    }
}
