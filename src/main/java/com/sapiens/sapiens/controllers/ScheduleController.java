package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.schedule.Schedule;
import com.sapiens.sapiens.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Schedule schedule) {
        return scheduleService.save(schedule);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Schedule schedule) {
        return scheduleService.update(schedule);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Schedule schedule) {
        return scheduleService.delete(schedule);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/discipline/{code}")
    public ResponseEntity<?> findByDisciplineCode(@PathVariable("code") String code) {
        return scheduleService.findByDisciplineCode(code);
    }

}
