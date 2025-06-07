package com.school.services;

import com.school.Repository.SystemUserRepository;
import com.school.Repository.TeacherRepository;
import com.school.models.School;
import com.school.models.SystemUser;
import com.school.models.Teacher;
import com.school.utils.BaseControllerActions;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeacherServices extends BaseControllerActions{
    private final TeacherRepository teacherRepository;
    private final SystemUserRepository systemUserRepository;

    @Transactional
    public Teacher saveTeacher(Teacher teacherRequest) {
        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("name");
        requires(requiredFields, teacherRequest);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SystemUser loggedInUser = systemUserRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("user not found"));

        School school = loggedInUser.getSchool();
        teacherRequest.setSchool(school);

        String randomPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        teacherRequest.setPasswordHash(randomPassword);
        if(teacherRepository.existsByPasswordHash(teacherRequest.getPasswordHash())) {
            throw new IllegalArgumentException("Password already exists");
        }

        return teacherRepository.save(teacherRequest);
    }

    public List<Teacher> fetchAllTeachers( ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SystemUser loggedInUser = systemUserRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("user not found"));

        School school = loggedInUser.getSchool();

        return teacherRepository.findAllBySchool(school);
    }


}
