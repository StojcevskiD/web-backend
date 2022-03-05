package com.example.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String username;

//    @Enumerated(value = EnumType.STRING)
//    private Role role;

//    private boolean isAccountNonExpired = true;
//    private boolean isAccountNonLocked = true;
//    private boolean isCredentialsNonExpired = true;
//    private boolean isEnabled = true;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
