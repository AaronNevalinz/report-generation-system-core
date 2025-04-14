package com.school.controllers;

import com.school.models.Marks;
import com.school.services.MarksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/marks")
public class MarksController {
    private final MarksService marksService;
    public MarksController(MarksService marksService) {
        this.marksService = marksService;
    }

    @GetMapping
    public List<Marks> getAllMarks() {
        return marksService.getMarks();
    }

    @PostMapping("/add/{studentId}")
    public Marks addMarks(@PathVariable Long studentId, @RequestParam Double marks) {
        return marksService.saveMarks(marks, studentId);
    }
}
