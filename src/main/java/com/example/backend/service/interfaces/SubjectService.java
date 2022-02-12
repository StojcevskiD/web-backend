package com.example.backend.service.interfaces;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubjectService {

    List<Subject> allSubjects();

    Subject findById(Long id);

    List<Subject> findAllSubjectsByYear(Year year);

    List<Subject> findAllSubjectsByYearAndSemesterType(Year year, SemesterType SemesterType);

    List<Subject> findAllSubjectsByName(String name);

    Page<Subject> findPaginatedSubjects(int pageNo, int pageSize);

    Void saveSubject(Subject subject);

    Subject findByNameAndYearAndSemesterType(String name, Year year, SemesterType semesterType);
}
