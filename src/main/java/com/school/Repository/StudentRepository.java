package com.school.Repository;

import com.school.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.classEntity.id = :classId")
    List<Student> findByClassId(@Param("classId") Long classId);
}
