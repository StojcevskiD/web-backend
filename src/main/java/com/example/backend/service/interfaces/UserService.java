package com.example.backend.service.interfaces;

import com.example.backend.model.User;
import org.springframework.web.bind.annotation.RequestHeader;

public interface UserService {
    User findUserByEmail (String email);
    void register(String email, String password, String username);
    void resetPassword(@RequestHeader String password);
}
