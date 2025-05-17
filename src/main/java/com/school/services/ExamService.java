package com.school.services;

import com.school.Repository.ClassRepository;
import com.school.Repository.ExamRepository;
import com.school.Repository.SubjectRepository;
import com.school.models.Exam;
import com.school.models.SchoolClass;
import com.school.utils.BaseControllerActions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExamService extends BaseControllerActions {
    private final ExamRepository examRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;

    public Exam SaveExam(Exam exam, Long classId) {
        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("name");
        requires(requiredFields, exam);

        if(!classRepository.existsById(classId)) {
            throw new IllegalArgumentException("classId does not exist");
        }

        SchoolClass schoolClass = classRepository.findById(classId).get();
        exam.setClassEntity(schoolClass);
        return examRepository.save(exam);
    }
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public List<Exam> getExamByClassId(Long classId) {
        if(!classRepository.existsById(classId)) {
            throw new IllegalArgumentException("classId does not exist");
        }
        return examRepository.findByClassEntityId(classId);
    }
}
