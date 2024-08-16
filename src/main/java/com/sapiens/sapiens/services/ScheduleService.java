package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.schedule.Schedule;
import com.sapiens.sapiens.repositories.ScheduleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleService {
    
    private final ScheduleRepository scheduleRepository;

    public ResponseEntity<?> save(Schedule schedule) {
        return ResponseEntity.ok().body(scheduleRepository.save(schedule));
    }

    public ResponseEntity<?> update(Schedule schedule) {
        return ResponseEntity.ok().body(scheduleRepository.save(schedule));
    }

    public ResponseEntity<?> delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
        return ResponseEntity.ok().body("Horário excluído com sucesso.");
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(scheduleRepository.findAll());
    }

    public ResponseEntity<?> findByDisciplineCode(String code) {
        return ResponseEntity.ok().body(scheduleRepository.findByDisciplineCode(code));
    }

}
