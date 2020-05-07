package com.april.beer.service;

import com.april.beer.dto.form_object.UserRegistrationFormObject;
import com.april.beer.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    User save(UserRegistrationFormObject registration);
}
