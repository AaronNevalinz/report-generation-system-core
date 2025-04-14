package com.school.controllers;

import com.school.models.Subject;
import com.school.services.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/subjects")
public class SubjectController {
    private final SubjectService subjectService;
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @GetMapping("/{id}")
    public Optional<Subject> getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping("/add")
    public Subject addSubject(@RequestParam String name) {
        return subjectService.addSubject(name);
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
}
