package com.school.Repository;

import com.school.models.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {

}
