package com.sapiens.sapiens.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.school.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<School> findByName(String name);

    List<School> findBySecretariatId(Long id);

    List<School> findByStudentsId(Long id);

    List<School> findByTeachersId(Long id);

    List<School> findByDisciplinesCode(String code);

    List<School> findBySchoolClassesCode(String code);

}
