package com.example.backend.web;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.service.interfaces.SemesterTypeService;
import com.example.backend.service.interfaces.SubjectService;
import com.example.backend.service.interfaces.YearService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final YearService yearService;
    private final SemesterTypeService semesterTypeService;

    //    @Autowired
    public SubjectController(SubjectService subjectService, YearService yearService, SemesterTypeService semesterTypeService) {
        this.subjectService = subjectService;
        this.yearService = yearService;
        this.semesterTypeService = semesterTypeService;
    }

    @GetMapping("/all")
    public List<Subject> getAllSubjects() {
        return subjectService.allSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Long id) {
        return subjectService.findById(id);
    }

//    @GetMapping("/{year}")
//    public List<Subject> getAllSubjectsByYear(@PathVariable String year) {
//        Year year1 = yearService.findByName(year);
//        return subjectService.findAllSubjectsByYear(year1);
//    }

    @GetMapping("/{godina}/{semestar}")
    public List<Subject> getAllSubjectsByYearAndSemester(@PathVariable(required = false) String godina, @PathVariable(required = false) String semestar) {
        if ((godina.equals(" ") || godina.isEmpty()) && (semestar.isEmpty() || semestar.equals(" "))) {
            return subjectService.allSubjects();
        } else {
            Year year = yearService.findByName(godina);
            SemesterType semesterType = semesterTypeService.findSemesterTypeByName(semestar);
            return subjectService.findAllSubjectsByYearAndSemesterType(year, semesterType);
        }
    }

    @GetMapping("/search")
    public List<Subject> searchByName(@RequestParam String searched) {
        return subjectService.findAlSubjectsByName(searched);
    }
}
