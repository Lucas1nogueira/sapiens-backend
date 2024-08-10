package com.sapiens.sapiens.domain.discipline;

import java.util.List;
import com.sapiens.sapiens.domain.evaluation.Evaluation;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.domain.teacher.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private String code;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    private SchoolClass schoolClass;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Evaluation> evaluation;

}