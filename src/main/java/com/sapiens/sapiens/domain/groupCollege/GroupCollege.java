package com.sapiens.sapiens.domain.groupCollege;

import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupCollege {
    
    @Id
    private String groupCode;
    private int studentAmount;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Discipline> disciplines;
    
}