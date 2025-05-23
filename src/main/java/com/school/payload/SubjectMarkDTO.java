package com.school.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectMarkDTO {
    private String subjectName;
    private Double score;
    private String grade;

    // Getters and setters
}
