package com.sapiens.sapiens.domain.grade;

import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.evaluation.Evaluation;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_grades")
public class Grade {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long value;

    @ManyToOne(fetch = FetchType.EAGER)
    private Discipline discipline;

    @OneToOne(fetch = FetchType.EAGER)
    private Evaluation evaluation;

}