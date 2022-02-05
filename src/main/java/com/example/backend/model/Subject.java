package com.example.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Year year;

    @ManyToMany
    private List<File> files;

    @ManyToOne
    private Semestar_Type semestar_type;

    public Subject() {
    }

    public Subject(String name, Semestar_Type semestar_type, Year year, File file) {
        this.name = name;
        this.year = year;
        this.semestar_type = semestar_type;
        this.files.add(file);
    }
}
