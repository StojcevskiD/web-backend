package com.example.backend.service.impl;


import com.example.backend.model.Exam_Type;
import com.example.backend.model.exceptions.ExamTypeNotFoundException;
import com.example.backend.repository.Exam_TypeRepository;
import com.example.backend.service.interfaces.Exam_TypeService;
import org.springframework.stereotype.Service;

@Service
public class Exam_TypeServiceImpl implements Exam_TypeService {

    private final Exam_TypeRepository exam_typeRepository;

    public Exam_TypeServiceImpl(Exam_TypeRepository exam_typeRepository) {
        this.exam_typeRepository = exam_typeRepository;
    }

    @Override
    public Exam_Type getType(Long id) {
        return exam_typeRepository.findById(id).orElseThrow(ExamTypeNotFoundException::new);
    }
}
