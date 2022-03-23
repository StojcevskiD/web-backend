package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(schema = "auth_user")
public class UserRoles extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;

}