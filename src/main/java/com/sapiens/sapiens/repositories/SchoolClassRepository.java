package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import java.util.Optional;
import java.util.List;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, String> {
    
    Optional<SchoolClass> findByCode(String code);

    boolean existsByCode(String code);

    List<SchoolClass> findByTeachersId(Long id);

    List<SchoolClass> findByStudentsId(Long id);

}
