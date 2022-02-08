package com.example.backend.service.interfaces;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;

import java.util.List;

public interface SubjectService {

    List<Subject> allSubjects();
    Subject findById(Long id);
    List<Subject> findAllSubjectsByYear(Year year);
    List<Subject> findAllSubjectsByYearAndSemesterType(Year year, SemesterType SemesterType);
    List<Subject> findAlSubjectsByName(String name);
}
