package com.sapiens.sapiens.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.services.AdminService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return adminService.findAll();
    }
}
