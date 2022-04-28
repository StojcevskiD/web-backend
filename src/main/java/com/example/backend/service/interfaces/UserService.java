package com.example.backend.service.interfaces;

import com.example.backend.model.Subject;
import com.example.backend.model.User;
import com.example.backend.model.dto.UserDetailsDto;
import com.example.backend.model.helpers.UserRegisterHelper;

public interface UserService {
    User findUserByEmail(String email);

    void register(String email, String password, UserRegisterHelper helper);

    //    void resetPassword(String password);
    boolean passwordMatches(User user, String password);

    UserDetailsDto getUserDetails();

    User findById(Long id);

    void takeSubject(User user, Subject subject);

    void removeSubject(User user, Subject subject);

}
