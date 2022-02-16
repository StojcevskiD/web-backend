package com.example.backend.web;


import com.example.backend.model.SemesterType;
import com.example.backend.model.Year;
import com.example.backend.service.interfaces.SemesterTypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/semester/type")
public class SemesterTypeController {

    private final SemesterTypeService semesterTypeService;

    public SemesterTypeController(SemesterTypeService semesterTypeService) {
        this.semesterTypeService = semesterTypeService;
    }

    @GetMapping("/all")
    public List<SemesterType> getAllSemesterTypes(){
        return semesterTypeService.findAll();
    }
}
