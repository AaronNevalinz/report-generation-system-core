package com.school.controllers;

import com.school.models.ClassEntity;
import com.school.services.ClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/classes")
public class ClassController {
    private ClassService classService;
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    public List<ClassEntity> getAllClasses() {
        return classService.getAllClasses();
    }

    @PostMapping("/add-class")
    public ClassEntity addClass(@RequestParam String name) {
        return classService.save(name);
    }
}
