package com.school.Repository;

import com.school.models.School;
import com.school.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Boolean existsByPasswordHash(String passwordHash);
    List<Teacher> findAllBySchool(School school);
}
