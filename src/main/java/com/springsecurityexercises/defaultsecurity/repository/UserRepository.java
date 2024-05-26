package com.springsecurityexercises.defaultsecurity.repository;

import com.springsecurityexercises.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("def")
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}