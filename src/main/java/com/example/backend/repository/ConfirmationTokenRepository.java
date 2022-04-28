package com.example.backend.repository;

import com.example.backend.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    @Query("select ct from ConfirmationToken ct where ct.token=:token")
    ConfirmationToken findByConfirmationToken(String token);

    @Transactional
    @Modifying //da moze i delete, update, insert da izvrsuva a ne samo "select"
    @Query("delete from ConfirmationToken ct where ct.user.id = :userId")
    void deleteTokensOfUser(Long userId);
}
