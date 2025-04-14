package com.school.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacher_id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "teacher_subject_allocations",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"subject_id", "teacher_id"})
    )
    @JsonManagedReference
    private List<Subject> subjects;

    public Teacher() {}

    public Teacher(String name) {
        this.name = name;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getTeachers().add(this);
    }
}
