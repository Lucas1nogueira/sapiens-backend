package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.services.SchoolClassService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/school-class")
@AllArgsConstructor
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.save(schoolClass);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.update(schoolClass);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> findByGroupCode(@PathVariable("groupCode") String code) {
        return schoolClassService.findByGroupCode(code);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return schoolClassService.findAll();
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<?> findByTeacherCode(@PathVariable("teacherId") Long id) {
        return schoolClassService.findByTeacherId(id);
    }

}
