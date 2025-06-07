package com.school.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentScoreDto {
    private String subjectName;
    private Double midTermResults;
    private Double endTermResults;
    private String teacherComment;
    private Double totalMarks;
    private Double average;
    private Double positionInClass;

    public StudentScoreDto(String subjectName, Double midTermResults, Double endTermResults, String teacherComment) {
        this.subjectName = subjectName;
        this.midTermResults = midTermResults;
        this.endTermResults = endTermResults;
        this.teacherComment = teacherComment;
    }
}
