package com.syrtin.beautybooking.controller;

import com.syrtin.beautybooking.dto.SpecialistDto;
import com.syrtin.beautybooking.service.SpecialistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/specialists")
public class SpecialistController {
    private final SpecialistService specialistService;

    public SpecialistController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    @PostMapping
    public ResponseEntity<SpecialistDto> createSpecialist(@Valid @RequestBody SpecialistDto specialistDTO) {
        SpecialistDto createdSpecialist = specialistService.createSpecialist(specialistDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpecialist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialistDto> getSpecialist(@PathVariable Long id) {
        SpecialistDto specialist = specialistService.getSpecialist(id);
        return ResponseEntity.ok(specialist);
    }

    @GetMapping
    public ResponseEntity<List<SpecialistDto>> getAllSpecialists() {
        List<SpecialistDto> specialists = specialistService.getAllSpecialists();
        return ResponseEntity.ok(specialists);
    }

    @GetMapping("/by-procedure/{procedureId}")
    public ResponseEntity<List<SpecialistDto>> getAllSpecialistsByProcedure(@PathVariable Long procedureId) {
        List<SpecialistDto> specialists = specialistService.getAllSpecialistsByProcedure(procedureId);
        return ResponseEntity.ok(specialists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialistDto> updateSpecialist(@PathVariable Long id, @Valid @RequestBody SpecialistDto specialistDTO) {
        SpecialistDto updatedSpecialist = specialistService.updateSpecialist(id, specialistDTO);
        return ResponseEntity.ok(updatedSpecialist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialist(@PathVariable Long id) {
        specialistService.deleteSpecialist(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{specialistId}/procedures/{procedureId}")
    public ResponseEntity<Void> addProcedure(@PathVariable Long specialistId, @PathVariable Long procedureId) {
        specialistService.addProcedure(specialistId, procedureId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{specialistId}/procedures/{procedureId}")
    public ResponseEntity<Void> removeProcedure(@PathVariable Long specialistId, @PathVariable Long procedureId) {
        specialistService.removeProcedure(specialistId, procedureId);
        return ResponseEntity.noContent().build();
    }
}