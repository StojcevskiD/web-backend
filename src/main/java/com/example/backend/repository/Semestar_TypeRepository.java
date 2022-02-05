package com.example.backend.repository;

import com.example.backend.model.Semestar_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Semestar_TypeRepository extends JpaRepository<Semestar_Type,Long> {
}
