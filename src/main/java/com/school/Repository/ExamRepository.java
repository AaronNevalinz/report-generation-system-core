package com.school.Repository;

import com.school.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("SELECT s from Exam s where s.subject.id = :subjectId")
    List<Exam> findBySubject(@Param("subjectId") Long subjectId);
    List<Exam> findByClassEntityId(Long classId);
}
