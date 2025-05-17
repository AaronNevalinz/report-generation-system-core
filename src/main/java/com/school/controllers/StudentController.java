package com.school.controllers;

import com.school.models.Student;
import com.school.models.Teacher;
import com.school.services.StudentService;
import com.school.utils.OperationReturnObject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;
    @PostMapping()
    public OperationReturnObject createStudent(@RequestBody Student studentRequest, @RequestParam(required = true) Long classId) {
        Student savedStudent = studentService.saveStudent(studentRequest, classId);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("studentId", savedStudent.getId());
        responseData.put("name", savedStudent.getName());

        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(201, "Created a Student", responseData);
        return returnObject;
    }

    @GetMapping()
    public OperationReturnObject fetchAllStudents() {
        List<Student> students = studentService.fetchAllStudents();
        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(200, "Fetched all students", students);
        return returnObject;
    }

    @DeleteMapping("/{student_id}")
    public OperationReturnObject deleteStudent(@PathVariable Long student_id) {
        String message = studentService.deleteStudentById(student_id);
        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setReturnCodeAndMessage(200, message);
        return returnObject;
    }

    @GetMapping("/class/{class_id}")
    public OperationReturnObject fetchAllStudentsByClass(@PathVariable Long class_id) {
        List<Student> students = studentService.getStudentsByClassId(class_id);
        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(200, "Fetched all students of classId: "+class_id, students);
        return returnObject;
    }

}
