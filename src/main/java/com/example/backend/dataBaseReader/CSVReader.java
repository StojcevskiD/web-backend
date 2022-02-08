package com.example.backend.dataBaseReader;

import com.example.backend.model.Exam_Type;
import com.example.backend.model.Semestar_Type;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.repository.Exam_TypeRepository;
import com.example.backend.repository.Semestar_TypeRepository;
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
    private final Semestar_TypeRepository semestar_typeRepository;
    private final YearRepository yearRepository;
    private final Exam_TypeRepository exam_typeRepository;

    public CSVReader(SubjectRepository subjectRepository, Semestar_TypeRepository semestar_typeRepository,
                     YearRepository yearRepository, Exam_TypeRepository exam_typeRepository) {
        this.subjectRepository = subjectRepository;
        this.semestar_typeRepository = semestar_typeRepository;
        this.yearRepository = yearRepository;
        this.exam_typeRepository = exam_typeRepository;
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
                } else if (fileName == "semestar_type.csv") {
                    createSemestar_Type(attributes);
                } else if (fileName == "exam_type.csv") {
                    createExam_Type(attributes);
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

        Semestar_Type semestar_type = semestar_typeRepository.findByName(semestar);

        Year year1 = yearRepository.findByName(year);

        // create and return book of this metadata
        Subject sub = new Subject(name, semestar_type, year1);
        subjectRepository.save(sub);
    }

    private void createYear(String[] metadata) {
        String name = metadata[1];
        Year year = new Year(name);
        yearRepository.save(year);
    }

    private void createSemestar_Type(String[] metadata) {
        String name = metadata[1];
        Semestar_Type sem = new Semestar_Type(name);
        semestar_typeRepository.save(sem);
    }

    private void createExam_Type(String[] metadata) {
        String name = metadata[1];
        Exam_Type exam = new Exam_Type(name);
        exam_typeRepository.save(exam);
    }
}
