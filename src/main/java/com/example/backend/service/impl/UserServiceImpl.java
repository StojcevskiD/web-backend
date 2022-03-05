package com.example.backend.service.impl;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void register(String email, String password, String username) {
        User user = new User(email, password, username);
        userRepository.save(user);
    }
}
