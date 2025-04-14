package com.school.controllers;

import com.school.models.Exam;
import com.school.services.ExamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exams")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public List<Exam> getAll() {
        return examService.getExams();
    }

    @PostMapping("/add/{classId}/{subjectId}")
    public Exam addExam(@PathVariable Long classId, @PathVariable Long subjectId, @RequestParam String name) {
        return examService.saveExam(classId, subjectId, name);
    }

    @GetMapping("/{id}")
    public Exam getExam(@PathVariable Long id) {
        return examService.getExam(id);
    }

    @GetMapping("/subject/{id}")
    public List<Exam> getExamBySubject(@PathVariable Long id) {
        return examService.getExamBySubject(id);
    }
}
