package com.example.backend.web;

import com.example.backend.model.Year;
import com.example.backend.service.interfaces.YearService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/year")
public class YearController {

    private final YearService yearService;

    public YearController(YearService yearService) {
        this.yearService = yearService;
    }

    @GetMapping("/all")
    public List<Year> getAllSubjects(){
        return yearService.allYears();
    }

}
