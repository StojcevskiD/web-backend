package com.example.backend.service.impl;

import com.example.backend.exceptions.SubjectNotFoundException;
import com.example.backend.model.SemestarType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.service.interfaces.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Subject> findAllSubjectsByYearAndSemestarType(Year year, SemestarType semestarType) {
        return subjectRepository.findAllByYearAndSemestarType(year, semestarType);
    }

    @Override
    public List<Subject> findAlSubjectsByName(String name) {
        return subjectRepository.findAllByNameLike(name);
    }
}

