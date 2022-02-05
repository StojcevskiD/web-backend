package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Semestar_Type{

   private String name;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @JsonIgnore
   @OneToMany (mappedBy = "semestar_type")
   private List<Subject> subjectList;

   public Semestar_Type(String name) {
      this.name = name;
   }

   public Semestar_Type() {
   }

}
