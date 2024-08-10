package com.sapiens.sapiens.domain.teacher;

import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.domain.user.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User {

    private String teacherCode;
    private int age;
    private String sex;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Discipline> disciplines;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<SchoolClass> schoolClasses;

}