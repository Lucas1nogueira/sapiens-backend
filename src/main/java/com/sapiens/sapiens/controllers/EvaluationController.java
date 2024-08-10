package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.evaluation.Evaluation;
import com.sapiens.sapiens.services.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/evaluation")
@AllArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Evaluation evaluation) {
        return evaluationService.save(evaluation);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Evaluation evaluation) {
        return evaluationService.update(evaluation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return evaluationService.findById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return evaluationService.findAll();
    }

}
