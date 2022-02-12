package com.example.backend.repository;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByYear(Year year);
    List<Subject> findAllByNameContainsIgnoreCase(String name);
    List<Subject> findAllByYearAndSemesterType(Year year, SemesterType semesterType);
    Subject findByNameAndYearAndSemesterType(String name, Year year, SemesterType semesterType);

}
