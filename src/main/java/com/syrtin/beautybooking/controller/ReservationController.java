package com.syrtin.beautybooking.controller;

import com.syrtin.beautybooking.dto.ReservationDto;
import com.syrtin.beautybooking.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> findReservationsByDateAndSpecialist(
            @RequestParam("reservationTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate,
            @RequestParam("specialistId") Long specialistId) {
        var reservationDtoList = reservationService.getReservationsByDateAndSpecialist(reservationDate, specialistId);
        return ResponseEntity.ok(reservationDtoList);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody ReservationDto reservationDTO) {
        var savedReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id,
                                                            @Valid @RequestBody ReservationDto reservationDTO) {
        var updateReservation = reservationService.updateReservation(id, reservationDTO);
        return ResponseEntity.ok(updateReservation);
    }
}