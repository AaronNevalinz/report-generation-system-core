package com.school.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private String studentName;
    private String className;
    private String examName;
    private List<SubjectMarkDTO> subjectMarks;
    private Double totalScore;
    private Double averageScore;
    private String overallGrade;
    private String overallComment;

}
