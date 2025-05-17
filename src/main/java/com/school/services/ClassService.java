package com.school.services;

import com.school.Repository.ClassRepository;
import com.school.models.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    public final ClassRepository classRepository;
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

//    save a class
    public SchoolClass saveClass(SchoolClass schoolClass) {
        if(classRepository.existsByName(schoolClass.getName())){
            throw new IllegalArgumentException("Class name already exists");
        }
        return classRepository.save(schoolClass);
    }

    public List<SchoolClass> findAll() {
        return classRepository.findAll();
    }
}
