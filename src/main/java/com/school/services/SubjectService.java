package com.school.services;

import com.school.Repository.SubjectRepository;
import com.school.models.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject addSubject(String name) {
        return subjectRepository.save(new Subject(name));
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
