package com.example.backend.service.interfaces;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.model.helpers.SubjectHelperAdd;
import com.example.backend.model.helpers.SubjectHelperEdit;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    List<Subject> allSubjects();

    Subject findById(Long id);

    List<Subject> findAllSubjectsByYear(Year year);

    List<Subject> findAllSubjectsByYearAndSemesterType(Year year, SemesterType SemesterType);

    List<Subject> findAllSubjectsByName(String name);

    Page<Subject> findPaginatedSubjects(int pageNo, int pageSize);

    void addSubject(SubjectHelperAdd subjectHelper);

    List<Subject> findAllByFullName(String name);

    void deleteById(Long id);

    void editSubject(SubjectHelperEdit subjectHelperEdit);

}
