package com.example.backend.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Subject {

    public Subject(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
