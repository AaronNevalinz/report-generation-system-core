package com.school.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Date dateOfBirth;
    private String gender;
    private String imageUrl;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String guardianName;
    private String guardianPhoneNumber;
    private String guardianAddress;
    private String guardianCity;
    private String guardianRelation;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Ignored when returning...
    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass classEntity;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Ignored when returning...
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Marks> marks;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
}
