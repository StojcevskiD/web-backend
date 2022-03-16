package com.example.backend.service.interfaces;

import com.example.backend.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findUserByEmail (String email);
    void register(String email, String password, String username);
//    void resetPassword(String password);
}
