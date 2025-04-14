package com.school.controllers;

import com.school.models.Teacher;
import com.school.services.TeacherServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TeacherController {
    private final TeacherServices teacherServices;
    public TeacherController(TeacherServices teacherServices) {
        this.teacherServices = teacherServices;
    }
    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherServices.getAllTeacher();
    }

    @PostMapping("/add-teacher")
    public Teacher addTeacher(@RequestParam String name) {
        return teacherServices.saveTeacher(name);
    }

    @PostMapping("/teacher/{id}")
    public Teacher getTeacher(@PathVariable Long id) {
        return teacherServices.getTeacherById(id);
    }

    @PostMapping("/allocate-subject")
    public Teacher allocateSubject(@RequestParam Long teacherId, @RequestParam Long subjectId) {
        return teacherServices.assignTeacherToSubject(teacherId, subjectId);
    }
}
