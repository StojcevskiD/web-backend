package com.example.backend.model.helpers;


import com.example.backend.model.SemesterType;
import com.example.backend.model.Year;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectHelper {

    private String name;
    private Long year;
    private Long semesterType;
}
