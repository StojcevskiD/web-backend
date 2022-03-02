package com.example.backend.service.interfaces;

import com.example.backend.model.User;

public interface AuthService {
    User login(String username, String password);

}
