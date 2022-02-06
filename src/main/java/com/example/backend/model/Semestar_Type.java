package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
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

}
