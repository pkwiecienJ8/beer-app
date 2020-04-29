package com.april.beer.service;

import com.april.beer.dto.UserRegistrationDto;
import com.april.beer.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
