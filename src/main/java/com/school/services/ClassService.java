package com.school.services;

import com.school.Repository.ClassRepository;
import com.school.models.ClassEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    private final ClassRepository classRepository;
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }
    public ClassEntity findById(Long classId) {
        return classRepository.findById(classId).get();
    }

    public ClassEntity save(String name) {
        return classRepository.save(new ClassEntity(name));
    }

}
