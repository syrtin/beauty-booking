package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.ReservationDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationDto createReservation(ReservationDto reservationDTO);

    List<ReservationDto> getReservationsByDateAndSpecialist(LocalDate reservationDate, Long specialistId);

    ReservationDto updateReservation(Long id, ReservationDto updatedReservationDto);

    void deleteReservation(Long id);
}