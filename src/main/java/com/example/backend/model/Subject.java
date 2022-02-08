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
    private Semestar_Type semestar_type;


    public Subject(String name, Semestar_Type semestar_type, Year year, File file) {
        this.name = name;
        this.year = year;
        this.semestar_type = semestar_type;
        this.files.add(file);
    }
    public Subject(String name, Semestar_Type semestar_type, Year year) {
        this.name = name;
        this.year = year;
        this.semestar_type = semestar_type;
    }
}
