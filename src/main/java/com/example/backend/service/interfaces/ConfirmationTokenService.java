package com.example.backend.service.interfaces;

import com.example.backend.model.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken findByConfirmationToken(String token);
    ConfirmationToken saveConfirmationToken(ConfirmationToken confirmationToken);
    void deleteToken(Long id);
    void deleteTokensOfUser(Long userId);
}
