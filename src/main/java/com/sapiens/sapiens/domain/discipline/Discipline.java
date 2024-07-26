package com.sapiens.sapiens.domain.discipline;

import com.sapiens.sapiens.domain.teacher.Teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_disciplines")
public class Discipline {
    
    @Id 
    private String disciplineCode;
    private String name;

    @ManyToOne
    private Teacher teacher;

}