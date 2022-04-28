package com.example.backend.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(schema = "auth_user")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private OffsetDateTime date_created;

    private OffsetDateTime expiration_date;

    public ConfirmationToken(User user, String token) {
        this.user = user;
        this.token = token;
        expiration_date = OffsetDateTime.now().plusMinutes(30);
    }
}
