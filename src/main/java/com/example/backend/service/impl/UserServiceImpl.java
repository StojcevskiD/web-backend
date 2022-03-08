package com.example.backend.service.impl;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void register(String email, String password, String username) {
        User user = new User(email, passwordEncoder.encode(password), username);
        userRepository.save(user);
    }

    @Override
    public void resetPassword(String password) {

    }
}
