package com.school.services;

import com.school.Repository.SubjectRepository;
import com.school.Repository.TeacherRepository;
import com.school.models.Subject;
import com.school.models.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServices {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    public TeacherServices(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

//    save Teacher
    public Teacher saveTeacher(String name){
        return teacherRepository.save(new Teacher(name));
    }
//    get all teachers
    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id){
        return teacherRepository.findById(id).orElseThrow(()-> new RuntimeException("Teacher Not Found"));
    }

    public Teacher assignTeacherToSubject(Long subjectId, Long teacherId){
        Teacher teacher = getTeacherById(teacherId);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(()-> new RuntimeException("Subject Not Found"));
        teacher.addSubject(subject);
        return teacherRepository.save(teacher);
    }
}
