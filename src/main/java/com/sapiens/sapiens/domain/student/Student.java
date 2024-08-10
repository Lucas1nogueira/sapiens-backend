package com.sapiens.sapiens.domain.student;

import com.sapiens.sapiens.domain.evaluation.Evaluation;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private SchoolClass schoolClass;
    
    @OneToOne(fetch = FetchType.EAGER)
    private Evaluation evaluation;
}