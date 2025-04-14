package com.school.services;

import com.school.Repository.ClassRepository;
import com.school.Repository.StudentRepository;
import com.school.models.ClassEntity;
import com.school.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassService classService;
    public StudentService(StudentRepository studentRepository, ClassService classService) {
        this.studentRepository = studentRepository;
        this.classService = classService;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    public Student save(String name,  Long classId) {
        ClassEntity classEntity = classService.findById(classId);
        return studentRepository.save(new Student(name, classEntity));
    }
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

//    get student by class
    public List<Student> getStudentsByClassId(Long classId) {
        return studentRepository.findByClassId(classId);
    }
}
