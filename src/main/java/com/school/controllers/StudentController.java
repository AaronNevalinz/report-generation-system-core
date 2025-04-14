package com.school.controllers;

import com.school.models.Student;
import com.school.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PostMapping("/add")
    public Student add(@RequestParam String name, @RequestParam Long classId) {
        return studentService.save(name, classId);
    }

    @GetMapping("/class/{classId}")
    public List<Student> getStudentsByClassId(@PathVariable Long classId) {
        return studentService.getStudentsByClassId(classId);
    }


}
