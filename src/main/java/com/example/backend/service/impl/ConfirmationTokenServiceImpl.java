package com.example.backend.service.impl;

import com.example.backend.model.ConfirmationToken;
import com.example.backend.repository.ConfirmationTokenRepository;
import com.example.backend.service.interfaces.ConfirmationTokenService;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    public ConfirmationToken findByConfirmationToken(String token) {
        return this.confirmationTokenRepository.findByConfirmationToken(token);
    }

    @Override
    public ConfirmationToken saveConfirmationToken(ConfirmationToken confirmationToken) {
        return this.confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public void deleteToken(Long id) {
        this.confirmationTokenRepository.deleteById(id);
    }

    @Override
    public void deleteTokensOfUser(Long userId) {
        this.confirmationTokenRepository.deleteTokensOfUser(userId);
    }
}
