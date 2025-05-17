package com.school.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass classEntity;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Teacher createdBy;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Marks> marks;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Report> reports;

    public void setCreatedBy(Teacher createdBy) {
        this.createdBy = createdBy;
    }

    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Exam() {}
    public Exam(String name, Subject subject, SchoolClass classEntity) {
        this.name = name;
        this.subject = subject;
        this.classEntity = classEntity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Subject getSubject() {
        return subject;
    }

    public SchoolClass getClassEntity() {
        return classEntity;
    }

    public Teacher getCreatedBy() {
        return createdBy;
    }

    public List<Marks> getMarks() {
        return marks;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setClassEntity(SchoolClass classEntity) {
        this.classEntity = classEntity;
    }
}
