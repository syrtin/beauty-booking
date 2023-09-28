package com.syrtin.beautybooking.controller;

import com.syrtin.beautybooking.dto.ProcedureDto;
import com.syrtin.beautybooking.service.ProcedureService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedures")
public class ProcedureController {
    private final ProcedureService procedureService;

    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping
    public List<ProcedureDto> getAllProcedures() {
        return procedureService.getAllProcedures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedureDto> getProcedureById(@PathVariable Long id) {
        ProcedureDto procedureDto = procedureService.getProcedureById(id);
        if (procedureDto != null) {
            return ResponseEntity.ok(procedureDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProcedureDto> createProcedure(@Valid @RequestBody ProcedureDto procedureDto) {
        ProcedureDto createdProcedureDto = procedureService.createProcedure(procedureDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcedureDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedureDto> updateProcedure(@PathVariable Long id, @Valid @RequestBody ProcedureDto procedureDto) {
        ProcedureDto updatedProcedureDto = procedureService.updateProcedure(id, procedureDto);
        if (updatedProcedureDto != null) {
            return ResponseEntity.ok(updatedProcedureDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedure(@PathVariable Long id) {
        procedureService.deleteProcedure(id);
        return ResponseEntity.noContent().build();
    }
}