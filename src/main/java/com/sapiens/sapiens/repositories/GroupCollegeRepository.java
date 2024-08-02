package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.groupCollege.GroupCollege;
import java.util.Optional;

@Repository
public interface GroupCollegeRepository extends JpaRepository<GroupCollege, String> {
    
    Optional<GroupCollege> findByGroupCode(String groupCode);

    boolean existsByGroupCode(String groupCode);

}
