package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.grade.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    
    boolean existsById(Long id);

}
