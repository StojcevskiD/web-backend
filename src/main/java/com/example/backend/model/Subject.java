package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
//@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Year year;

    @JsonIgnore
    @OneToMany
    private List<File> files;

    @ManyToOne
    private SemestarType semestarType;


    public Subject(String name, SemestarType semestarType, Year year, File file) {
        this.name = name;
        this.year = year;
        this.semestarType = semestarType;
        this.files.add(file);
    }
    public Subject(String name, SemestarType semestarType, Year year) {
        this.name = name;
        this.year = year;
        this.semestarType = semestarType;
    }
}
