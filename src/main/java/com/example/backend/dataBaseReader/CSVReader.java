package com.example.backend.dataBaseReader;

import com.example.backend.model.ExamType;
import com.example.backend.model.SemestarType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.repository.ExamTypeRepository;
import com.example.backend.repository.SemestarTypeRepository;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.repository.YearRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component

public class CSVReader {

    private final SubjectRepository subjectRepository;
    private final SemestarTypeRepository semestarTypeRepository;
    private final YearRepository yearRepository;
    private final ExamTypeRepository examTypeRepository;

    public CSVReader(SubjectRepository subjectRepository, SemestarTypeRepository semestarTypeRepository,
                     YearRepository yearRepository, ExamTypeRepository examTypeRepository) {
        this.subjectRepository = subjectRepository;
        this.semestarTypeRepository = semestarTypeRepository;
        this.yearRepository = yearRepository;
        this.examTypeRepository = examTypeRepository;
    }

    public void readSubjectsFromCSV(String fileName) throws IOException {

        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");


                if (fileName == "subjects.csv") {
                    createSubject(attributes);
                } else if (fileName == "SemestarType.csv") {
                    createSemestarType(attributes);
                } else if (fileName == "ExamType.csv") {
                    createExamType(attributes);
                } else if (fileName == "year.csv") {
                    createYear(attributes);
                }

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }
            return;
        }

    }

    private void createSubject(String[] metadata) {
        String name = metadata[1];

        String semestar = metadata[2];

        String year = metadata[3];

        SemestarType semestarType = semestarTypeRepository.findByName(semestar);

        Year year1 = yearRepository.findByName(year);

        // create and return book of this metadata
        Subject sub = new Subject(name, semestarType, year1);
        subjectRepository.save(sub);
    }

    private void createYear(String[] metadata) {
        String name = metadata[1];
        Year year = new Year(name);
        yearRepository.save(year);
    }

    private void createSemestarType(String[] metadata) {
        String name = metadata[1];
        SemestarType sem = new SemestarType(name);
        semestarTypeRepository.save(sem);
    }

    private void createExamType(String[] metadata) {
        String name = metadata[1];
        ExamType exam = new ExamType(name);
        examTypeRepository.save(exam);
    }
}
