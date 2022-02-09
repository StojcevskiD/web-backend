package com.example.backend.service.interfaces;

import com.example.backend.model.SemesterType;

public interface SemesterTypeService {
    SemesterType findSemesterTypeByName(String name);
    SemesterType findById(Long id);
}
