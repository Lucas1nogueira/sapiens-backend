package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.discipline.Discipline;
import java.util.Optional;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, String> {
    
    Optional<Discipline> findByCode(String code);
    
    Optional<Discipline> findByName(String name);

}
