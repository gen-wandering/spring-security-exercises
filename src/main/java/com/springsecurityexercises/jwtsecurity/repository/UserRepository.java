package com.springsecurityexercises.jwtsecurity.repository;

import com.springsecurityexercises.jwtsecurity.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Customer, Integer> {
    Customer findByName(String name);
}
