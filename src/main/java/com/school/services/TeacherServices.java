package com.school.services;

import com.school.Repository.TeacherRepository;
import com.school.models.Teacher;
import com.school.utils.BaseControllerActions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeacherServices extends BaseControllerActions{
    private final TeacherRepository teacherRepository;

    public Teacher saveTeacher(Teacher teacherRequest) {
        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("name");
        requires(requiredFields, teacherRequest);

        String randomPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        teacherRequest.setPasswordHash(randomPassword);
        if(teacherRepository.existsByPasswordHash(teacherRequest.getPasswordHash())) {
            throw new IllegalArgumentException("Password already exists");
        }

        return teacherRepository.save(teacherRequest);
    }

    public List<Teacher> fetchAllTeachers() {
        return teacherRepository.findAll();
    }


}
