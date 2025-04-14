package com.school.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double marks_obtained;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    public Marks() {}

    public Marks(Double marks_obtained, Student student) {
        this.marks_obtained = marks_obtained;
        this.student = student;
    }

    public Double getMarks_obtained() {
        return marks_obtained;
    }

    public void setMarks_obtained(Double marks_obtained) {
        this.marks_obtained = marks_obtained;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
