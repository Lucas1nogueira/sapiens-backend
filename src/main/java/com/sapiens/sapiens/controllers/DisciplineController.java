package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.services.DisciplineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/discipline")
@AllArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Discipline discipline) {
        return disciplineService.save(discipline);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Discipline discipline) {
        return disciplineService.update(discipline);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Discipline discipline) {
        return disciplineService.delete(discipline);
    }

    @GetMapping("/disciplineCode/{disciplineCode}")
    public ResponseEntity<?> findByDisciplineCode(@PathVariable("disciplineCode") String disciplineCode) {
        return disciplineService.findByDisciplineCode(disciplineCode);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name") String name) {
        return disciplineService.findByName(name);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return disciplineService.findAll();
    }

}
