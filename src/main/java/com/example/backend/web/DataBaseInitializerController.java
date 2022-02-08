package com.example.backend.web;

import com.example.backend.dataBaseReader.CSVReader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/database")
public class DataBaseInitializerController {

    private final CSVReader csvReader;

    public DataBaseInitializerController(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    @GetMapping("")
    public void loadDataBase() throws IOException {
        csvReader.readSubjectsFromCSV("year.csv");
        csvReader.readSubjectsFromCSV("ExamType.csv");
        csvReader.readSubjectsFromCSV("SemestarType.csv");
        csvReader.readSubjectsFromCSV("subjects.csv");
    }
}
