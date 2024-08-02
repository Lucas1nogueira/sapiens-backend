package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.groupCollege.GroupCollege;
import com.sapiens.sapiens.services.GroupCollegeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/groupCollege")
@AllArgsConstructor
public class GroupCollegeController {

    private final GroupCollegeService groupCollegeService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody GroupCollege groupCollege) {
        return groupCollegeService.save(groupCollege);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody GroupCollege groupCollege) {
        return groupCollegeService.update(groupCollege);
    }

    @GetMapping("/groupCode/{groupCode}")
    public ResponseEntity<?> findByGroupCode(@PathVariable("groupCode") String groupCode) {
        return groupCollegeService.findByGroupCode(groupCode);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return groupCollegeService.findAll();
    }

}
