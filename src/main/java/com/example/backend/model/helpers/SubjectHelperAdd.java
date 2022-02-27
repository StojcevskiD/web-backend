package com.example.backend.model.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectHelperAdd {

    private String name;
    private Long year;
    private Long semesterType;
}
