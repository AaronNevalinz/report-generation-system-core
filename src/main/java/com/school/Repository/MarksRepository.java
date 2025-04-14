package com.school.Repository;

import com.school.models.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarksRepository extends JpaRepository<Marks, Long> {
}
