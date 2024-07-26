package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.student.Student;
import com.sapiens.sapiens.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @GetMapping("/matriculation/{matriculation}")
    public ResponseEntity<?> findBMatriculationEmail(@PathVariable("matriculation") String matriculation) {
        return studentService.findByMatriculation(matriculation);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        return studentService.findByEmail(email);
    }

}
