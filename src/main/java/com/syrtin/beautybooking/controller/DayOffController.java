package com.syrtin.beautybooking.controller;

import com.syrtin.beautybooking.dto.DayOffDto;
import com.syrtin.beautybooking.service.DayOffService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day-off")
public class DayOffController {

    private final DayOffService dayOffService;

    public DayOffController(DayOffService dayOffService) {
        this.dayOffService = dayOffService;
    }

    @PostMapping
    public ResponseEntity<DayOffDto> createDayOff(@RequestBody @Valid DayOffDto dayOffDto) {
        DayOffDto createdDayOffDto = dayOffService.createDayOff(dayOffDto);
        return ResponseEntity.ok(createdDayOffDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DayOffDto> getDayOffById(@PathVariable Long id) {
        DayOffDto dayOffDto = dayOffService.getDayOffById(id);
        return ResponseEntity.ok(dayOffDto);
    }

    @GetMapping
    public ResponseEntity<List<DayOffDto>> getAllDayOffs() {
        List<DayOffDto> dayOffs = dayOffService.getAllDayOffs();
        return ResponseEntity.ok(dayOffs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DayOffDto> updateDayOff(@PathVariable Long id,
                                                  @RequestBody @Valid DayOffDto dayOffDto
    ) {
        DayOffDto updatedDayOffDto = dayOffService.updateDayOff(id, dayOffDto);
        return ResponseEntity.ok(updatedDayOffDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDayOff(@PathVariable Long id) {
        dayOffService.deleteDayOff(id);
        return ResponseEntity.noContent().build();
    }
}