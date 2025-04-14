package com.school.services;

import com.school.Repository.ClassRepository;
import com.school.Repository.ExamRepository;
import com.school.Repository.SubjectRepository;
import com.school.models.ClassEntity;
import com.school.models.Exam;
import com.school.models.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    private ExamRepository examRepository;
    private SubjectRepository subjectRepository;
    private ClassRepository classRepository;
    public ExamService(ExamRepository examRepository, SubjectRepository subjectRepository, ClassRepository classRepository) {
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.classRepository = classRepository;
    }
    public List<Exam> getExams() {
        return examRepository.findAll();
    }
    public Exam getExam(Long id) {
        return examRepository.findById(id).get();
    }
    public Exam saveExam(Long classId, Long subjectId, String name) {
        Subject subject = subjectRepository.findById(subjectId).get();
        ClassEntity classEntity = classRepository.findById(classId).get();
        return examRepository.save(new Exam(name, subject, classEntity));
    }

    public List<Exam> getExamBySubject(Long subjectId) {
        return examRepository.findBySubject(subjectId);
    }
}
