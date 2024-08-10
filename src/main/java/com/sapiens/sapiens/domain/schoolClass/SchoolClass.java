package com.sapiens.sapiens.domain.schoolClass;

import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.student.Student;
import com.sapiens.sapiens.domain.teacher.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_school_classes")
public class SchoolClass {
    
    @Id
    private String code;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Discipline> disciplines;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Teacher> teachers;
    
}