package com.school.services;

import com.school.Repository.SubjectRepository;
import com.school.models.Subject;
import com.school.utils.BaseControllerActions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService extends BaseControllerActions {
    private final SubjectRepository subjectRepository;

    public Subject saveSubject(Subject subjectRequest) {
        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("name");
        requires(requiredFields, subjectRequest);
        if(subjectRepository.existsSubjectsByName(subjectRequest.getName())) {
            throw new IllegalArgumentException("Subject name already exists");
        }
        return subjectRepository.save(subjectRequest);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
