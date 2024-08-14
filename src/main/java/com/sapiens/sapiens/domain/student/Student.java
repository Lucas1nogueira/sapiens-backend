package com.sapiens.sapiens.domain.student;

import java.util.List;
import com.sapiens.sapiens.domain.grade.Grade;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    
    private String matriculation;
    private int age;
    private String sex;

    @ManyToOne(fetch = FetchType.EAGER)
    private SchoolClass schoolClass;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<Grade> grades;

}