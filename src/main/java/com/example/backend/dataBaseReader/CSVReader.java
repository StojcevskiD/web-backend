package com.example.backend.dataBaseReader;

import com.example.backend.model.Semestar_Type;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component

public class CSVReader {


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

                Subject subject = createSubject(attributes);

                // adding subject into DataBase


                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        }

    }

      private static Subject createSubject(String[] metadata) {
        String name = metadata[0];

        String semestar  =metadata[1];

        String year =metadata[2];

        Semestar_Type semestar_type = new Semestar_Type(semestar);

        Year year1 = new Year(year);

        // create and return book of this metadata
        return new Subject(name, semestar_type, year1,null);
    }
}
