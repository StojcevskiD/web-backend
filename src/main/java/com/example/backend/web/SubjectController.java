package com.example.backend.web;

import com.example.backend.model.Subject;
import com.example.backend.service.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

//    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public List<Subject> getAllSubjects(){
        return subjectService.allSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Long id){
        return subjectService.findById(id);
    }
}
