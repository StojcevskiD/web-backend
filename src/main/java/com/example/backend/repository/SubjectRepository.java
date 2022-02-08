package com.example.backend.repository;

import com.example.backend.model.SemestarType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByYear(Year year);
    List<Subject> findAllByNameLike(String name);
    List<Subject> findAllByYearAndSemestarType(Year year, SemestarType semestarType);
}
