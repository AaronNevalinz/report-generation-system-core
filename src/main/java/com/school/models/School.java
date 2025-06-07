package com.school.models;

import com.school.models.enums.ClassLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<SystemUser> users = new ArrayList<>();
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<SchoolClass> classLevels = new ArrayList<>();
}
