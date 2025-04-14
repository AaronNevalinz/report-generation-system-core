package com.school.services;

import com.school.Repository.MarksRepository;
import com.school.models.Marks;
import com.school.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {
    private final MarksRepository marksRepository;
    private final StudentService studentService;
    public MarksService(MarksRepository marksRepository, StudentService studentService) {
        this.marksRepository = marksRepository;
        this.studentService = studentService;
    }
    public List<Marks> getMarks() {
        return marksRepository.findAll();
    }
    public Marks getMarksById(Long id) {
        return marksRepository.findById(id).get();
    }
    public Marks saveMarks(Double marks, Long studentId) {
        Student student = studentService.findById(studentId);
        return marksRepository.save(new Marks(marks, student));
    }
}
