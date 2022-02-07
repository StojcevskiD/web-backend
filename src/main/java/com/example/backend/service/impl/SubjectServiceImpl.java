package com.example.backend.service.impl;

import com.example.backend.model.Subject;
import com.example.backend.model.exceptions.SubjectNotFoundException;
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
}