package com.example.backend.web;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.model.exceptions.InvalidInputException;
import com.example.backend.model.exceptions.SubjectAlreadyExistsException;
import com.example.backend.model.helpers.SubjectHelper;
import com.example.backend.service.interfaces.SemesterTypeService;
import com.example.backend.service.interfaces.SubjectService;
import com.example.backend.service.interfaces.YearService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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

    @GetMapping("/filter/semester")
    public List<Subject> getAllSubjectsByYearAndSemester(@RequestParam (required = false) Long semesterId, @RequestParam Long yearId) {
        Year year = yearService.getYear(yearId);
        if (semesterId == null) {
            return subjectService.findAllSubjectsByYear(year);
        } else {
            SemesterType semesterType = semesterTypeService.findById(semesterId);
            return subjectService.findAllSubjectsByYearAndSemesterType(year, semesterType);
        }
    }

    @GetMapping("/search")
    public List<Subject> searchByName(@RequestParam String value) {
        return subjectService.findAllSubjectsByName(value);
    }

    @GetMapping("/page/{pageNo}/{pageSize}")
    public List<Subject> findPaginated(@PathVariable int pageNo, @PathVariable int pageSize) {
        Page<Subject> page = subjectService.findPaginatedSubjects(pageNo, pageSize);
        return page.getContent();
    }

   @GetMapping("/totalSubjects")
   public int findNumberOfTotalSubjects(){
        return subjectService.allSubjects().size();
   }

    @PostMapping("/add")
    public void addSubject(@RequestBody SubjectHelper subjectHelper) {
        if(subjectService.findAllSubjectsByName(subjectHelper.getName()).size()!=0){
            throw new SubjectAlreadyExistsException();
        }
        SemesterType semesterType =semesterTypeService.findById(subjectHelper.getSemesterType());
        Year year = yearService.getYear(subjectHelper.getYear());
        Subject newSubject = new Subject(subjectHelper.getName(), semesterType, year);
        subjectService.saveSubject(newSubject);
    }
}
