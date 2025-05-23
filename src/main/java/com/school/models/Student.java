package com.school.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Ignored when returning...
    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass classEntity;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Ignored when returning...
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Marks> marks;


    public Student() {}
    public Student(String name, SchoolClass classEntity) {
        this.name = name;
        this.classEntity = classEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SchoolClass getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(SchoolClass classEntity) {
        this.classEntity = classEntity;
    }

    public List<Marks> getMarks() {
        return marks;
    }

    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }
}
