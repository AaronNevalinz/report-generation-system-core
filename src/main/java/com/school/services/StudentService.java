package com.school.services;

import com.school.Repository.ClassRepository;
import com.school.Repository.StudentRepository;
import com.school.models.SchoolClass;
import com.school.models.Student;
import com.school.models.Teacher;
import com.school.utils.BaseControllerActions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StudentService extends BaseControllerActions {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    public Student saveStudent(Student studentRequest, Long classId) {
        List<String> requiredFields = Arrays.asList("name");
        requires(requiredFields, studentRequest);
        if(!classRepository.existsById(classId)) {
            throw new IllegalArgumentException("Class not found");
        }
        SchoolClass schoolClass = classRepository.findById(classId).get();
        studentRequest.setClassEntity(schoolClass);

        return studentRepository.save(studentRequest);
    }

    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    public String deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student by id " + id + " does not exist");
        }else {
            studentRepository.deleteById(id);
            return "Student Deleted Successfully";
        }
    }

    public List<Student> getStudentsByClassId(Long classId) {
        return studentRepository.findBySchoolClassId(classId);
    }
}
