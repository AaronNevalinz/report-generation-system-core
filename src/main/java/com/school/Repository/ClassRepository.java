package com.school.Repository;

import com.school.models.SchoolClass;
import com.school.models.enums.ClassLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<SchoolClass, Long> {
    Boolean existsByName(ClassLevel className);
}
