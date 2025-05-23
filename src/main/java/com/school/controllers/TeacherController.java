package com.school.controllers;

import com.school.models.Teacher;
import com.school.services.TeacherServices;
import com.school.utils.OperationReturnObject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherServices teacherServices;
    @PostMapping()
    public OperationReturnObject createTeacher(@RequestBody Teacher teacherRequest) {
        Teacher savedTeacher = teacherServices.saveTeacher(teacherRequest);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("teacherId", savedTeacher.getTeacher_id());
        responseData.put("name", savedTeacher.getName());
        responseData.put("passwordHash", savedTeacher.getPasswordHash());

        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(201, "Created a teacher", responseData);
        return returnObject;
    }

    @GetMapping()
    public OperationReturnObject fetchAllTeachers() {
        List<Teacher> teachers = teacherServices.fetchAllTeachers();
        OperationReturnObject returnObject = new OperationReturnObject();
        returnObject.setCodeAndMessageAndReturnObject(200, "Fetching all teachers", teachers);
        return returnObject;
    }
}
