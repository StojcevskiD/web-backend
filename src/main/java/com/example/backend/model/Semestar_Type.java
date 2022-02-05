package com.example.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Semestar_Type{

   private String name;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   public Semestar_Type(String name) {
      this.name = name;
   }

   public Semestar_Type() {
   }

}
