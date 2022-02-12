package com.example.backend.web;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.model.exceptions.InvalidInputException;
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

    @GetMapping("/filter/year")
    public List<Subject> getAllSubjectsByYear(@RequestParam Long yearId) {
        Year year = yearService.getYear(yearId);
        return subjectService.findAllSubjectsByYear(year);
    }

    @GetMapping("/filter/semester")
    public List<Subject> getAllSubjectsByYearAndSemester(@RequestParam Long semesterId, @RequestParam Long yearId) {
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
        List<Subject> subjects = page.getContent();
        return subjects;
    }

   @GetMapping("/totalSubjects")
   public int findNumberOfTotalSubjects(){
        return subjectService.allSubjects().size();
   }

    @PostMapping("/add")
    public void addSubject(@RequestParam String name, @RequestParam String year, @RequestParam String semesterType) {
        if (name.isEmpty() || year.isEmpty() || semesterType.isEmpty()) {
            throw new InvalidInputException();
        }

        SemesterType semesterType1 =semesterTypeService.findSemesterTypeByName(semesterType);
        Year year1 = yearService.findByName(year);
        Subject oldSubject = subjectService.findByNameAndYearAndSemesterType(name, year1, semesterType1);
        if (oldSubject != null) {
            return;
        }
        Subject newSubject = new Subject(name, semesterType1, year1);
        subjectService.saveSubject(newSubject);
    }
}
