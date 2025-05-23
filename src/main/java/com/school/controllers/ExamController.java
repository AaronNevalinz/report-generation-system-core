package com.school.controllers;

import com.school.models.Exam;
import com.school.services.ExamService;
import com.school.utils.OperationReturnObject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/exams")
public class ExamController {
    private final ExamService examService;

    @PostMapping
    public OperationReturnObject createExam(@RequestBody Exam examRequest, @RequestParam(required = true) Long classId) {
        Exam exam = examService.SaveExam(examRequest, classId);
        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(201, "Exam created Successfully for classId: " + classId, exam);
        return returnObject;
    }

    @GetMapping
    public OperationReturnObject fetchAllExams() {
        List<Exam> exams = examService.getAllExams();
        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(200, "All Exams", exams);
        return returnObject;
    }

    @GetMapping("/class/{classId}")
    public OperationReturnObject fetchExamByClassId(@PathVariable Long classId) {
        List<Exam> exams = examService.getExamByClassId(classId);
        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(200, "Exam By ClassId: " + classId, exams);
        return returnObject;
    }

}
