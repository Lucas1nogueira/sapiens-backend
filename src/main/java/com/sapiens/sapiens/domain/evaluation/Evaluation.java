package com.sapiens.sapiens.domain.evaluation;

import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.grade.Grade;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_evaluations")
public class Evaluation {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Discipline discipline;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "evaluation")
    private List<Grade> grades;

}