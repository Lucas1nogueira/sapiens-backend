package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.grade.Grade;
import com.sapiens.sapiens.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/grade")
@AllArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Grade grade) {
        return gradeService.save(grade);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Grade grade) {
        return gradeService.update(grade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return gradeService.findById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return gradeService.findAll();
    }

}
