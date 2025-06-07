package com.school.Repository;

import com.school.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    Optional<SystemUser> findByUsername(String username);
    List<SystemUser> findByActive(Integer active);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
