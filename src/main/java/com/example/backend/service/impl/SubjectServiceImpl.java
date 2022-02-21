package com.example.backend.service.impl;

import com.example.backend.model.exceptions.SubjectNotFoundException;
import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.service.interfaces.SubjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> allSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
    }

    @Override
    public List<Subject> findAllSubjectsByYear(Year year) {
        return subjectRepository.findAllByYear(year);
    }

    @Override
    public List<Subject> findAllSubjectsByYearAndSemesterType(Year year, SemesterType semesterType) {
        return subjectRepository.findAllByYearAndSemesterType(year, semesterType);
    }

    @Override
    public List<Subject> findAllSubjectsByName(String name) {
        return subjectRepository.findAllByNameContainsIgnoreCase(name);
    }

    @Override
    public Page<Subject> findPaginatedSubjects(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return subjectRepository.findAll(pageable);
    }

    @Override
    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public Subject findByNameAndYearAndSemesterType(String name, Year year, SemesterType semesterType) {
        return subjectRepository.findByNameAndYearAndSemesterType(name,year,semesterType);
    }

    @Override
    public Subject findSubjectByName(String name) {
        return subjectRepository.findSubjectByNameIgnoreCase(name);
    }

}

