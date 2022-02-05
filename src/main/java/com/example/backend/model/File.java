package com.example.backend.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;


@Entity
@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Lob
    @NotNull
    private Blob data;

    public File(String name, Blob data, Subject subjects) {
        this.name = name;
        this.data = data;
        this.subject=subjects;
    }

    //zaradi diskretna sto e i vo leten i vo zimski semestar kako poseben predmet?
    @ManyToOne
    private Subject subject;

    public File() {
    }

    public String getName() {
        return this.name;
    }
}
