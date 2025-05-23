package com.school.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacher_id;
    private String name;
    private String passwordHash;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Exam> exams;

    @OneToMany(mappedBy = "enteredBy", cascade = CascadeType.ALL)
    List<Marks> marks;

//    @OneToMany(mappedBy = "approvedBy", cascade = CascadeType.ALL)
//    List<Report> reports;

}
