package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.teacher.Teacher;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
    Optional<Teacher> findByTeacherCode(String teacherCode);

    Optional<Teacher> findByEmail(String email);

    boolean existsByEmail(String email);

}