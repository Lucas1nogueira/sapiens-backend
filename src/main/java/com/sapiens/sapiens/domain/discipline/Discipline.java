package com.sapiens.sapiens.domain.discipline;

import com.sapiens.sapiens.domain.groupCollege.GroupCollege;
import com.sapiens.sapiens.domain.teacher.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_disciplines")
public class Discipline {
    
    @Id 
    private String disciplineCode;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupCollege group;

}