package com.school.controllers;

import com.school.models.Subject;
import com.school.services.SubjectService;
import com.school.utils.OperationReturnObject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping()
    public OperationReturnObject createSubject(@RequestBody Subject subjectRequest) {
        Subject savedSubject = subjectService.saveSubject(subjectRequest);
        OperationReturnObject operationReturnObject = new OperationReturnObject();
        operationReturnObject.setCodeAndMessageAndReturnObject(201, "Subject Created Successfully", savedSubject);
        return operationReturnObject;
    }

    @GetMapping()
    public OperationReturnObject getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        OperationReturnObject operationReturnObject = new OperationReturnObject();
        operationReturnObject.setCodeAndMessageAndReturnObject(200, "All Subjects", subjects);
        return operationReturnObject;
    }
}
