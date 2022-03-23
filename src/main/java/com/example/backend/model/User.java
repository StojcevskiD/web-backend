package com.example.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users", schema = "auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String username;

    private String name;

    private String surname;

    private String address;

    private String phone;

    private LocalDateTime date_created;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoles> roles;


    public User(String email, String password, String username, String name, String surname, String address,
                String phone, LocalDateTime date_created) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date_created = date_created;
    }
}
