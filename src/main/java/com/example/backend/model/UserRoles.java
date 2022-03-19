package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class UserRoles extends BaseEntity {

    @ManyToOne
//    @JoinColumn(name = "au_user_id")
    private User user;

    @ManyToOne
//    @JoinColumn(name = "au_role_id")
    private Role role;

}