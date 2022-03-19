package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity

@Getter
@Setter
public class Role extends BaseEntity {

    private String name;

    private String label;

    @Column(name = "label_sq")
    private String labelSq;

}
