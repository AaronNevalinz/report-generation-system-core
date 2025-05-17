package com.school.controllers;

import com.school.models.Marks;
import com.school.services.MarksService;
import com.school.utils.OperationReturnObject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/marks")
public class MarksController {
    private final MarksService marksService;

    @PostMapping("/{exam_id}")
    public OperationReturnObject addMarks(@RequestBody Marks marksRequest, @PathVariable Long exam_id, @RequestParam("studentId") Long studentId, @RequestParam("subjectId") Long subjectId) {
        Marks marks = marksService.saveMarks(marksRequest, exam_id, studentId, subjectId);
        OperationReturnObject operationReturnObject = new OperationReturnObject();
        operationReturnObject.setCodeAndMessageAndReturnObject(200, "Added Student Marks", marks);
        return operationReturnObject;
    }

}
