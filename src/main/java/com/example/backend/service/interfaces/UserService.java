package com.example.backend.service.interfaces;

import com.example.backend.model.User;

public interface UserService {
    User findUserByEmail(String email);

    void register(String email, String password, String username);

    //    void resetPassword(String password);
    boolean passwordMatches(User user, String password);

}
