package com.sapiens.sapiens.domain.teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.user.User;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User{

    private String teacherCode;
    private int age;
    private String sex;

    @OneToMany
    private List<Discipline> disciplina;

}