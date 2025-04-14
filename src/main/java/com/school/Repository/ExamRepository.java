package com.school.Repository;

import com.school.models.Exam;
import com.school.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("SELECT s from Exam s where s.subject.id = :subjectId")
    List<Exam> findBySubject(@Param("subjectId") Long subjectId);
}
