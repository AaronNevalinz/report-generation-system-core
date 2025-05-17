package com.school.services;

import com.school.Repository.*;
import com.school.models.Exam;
import com.school.models.Marks;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.utils.BaseControllerActions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class MarksService extends BaseControllerActions {
    private final MarksRepository marksRepository;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public Marks saveMarks(Marks marksRequest, Long exam_id, Long student_id, Long subjectId) {
        List<String> requiredFields = Arrays.asList("marksObtained");
        requires(requiredFields, marksRequest);

        if(!subjectRepository.existsById(subjectId)) {
            throw new IllegalArgumentException("subjectId does not exist");
        }
        if(!studentRepository.existsById(student_id)) {
            throw new IllegalArgumentException("Student does not exist");
        }
        if(!examRepository.existsById(exam_id)) {
            throw new IllegalArgumentException("Marks does not exist");
        }
        Student student = studentRepository.findById(student_id).get();
        Exam exam = examRepository.findById(exam_id).get();
        Subject subject = subjectRepository.findById(subjectId).get();

        marksRequest.setStudent(student);
        marksRequest.setExam(exam);
        marksRequest.setSubject(subject);
        return marksRepository.save(marksRequest);
    }
}
