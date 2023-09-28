package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.ReservationDto;
import com.syrtin.beautybooking.exception.DataNotFoundException;
import com.syrtin.beautybooking.exception.ScheduleConflictException;
import com.syrtin.beautybooking.mapper.ReservationMapper;
import com.syrtin.beautybooking.model.Reservation;
import com.syrtin.beautybooking.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDTO) {
        log.info("Creating a new reservation");

        Reservation reservation = reservationMapper.toEntity(reservationDTO);

        var reservationDate = reservation.getReservationTime().toLocalDate();
        var startTime = reservation.getReservationTime();
        var endTime = reservation.getReservationTime().plusMinutes(reservation.getProcedure().getDuration());

        List<Reservation> existingReservations = findReservationsByDateAndSpecialist(
                reservationDate, reservation.getSpecialist().getId());

        for (Reservation currentReservation : existingReservations) {
            var existingEndTime = currentReservation.getReservationTime()
                    .plusMinutes(currentReservation.getProcedure().getDuration());
            if (startTime.isBefore(existingEndTime) && endTime.isAfter(currentReservation.getReservationTime())) {
                throw new ScheduleConflictException("Reservation cannot be saved due to schedule conflict.");
            }
        }

        var savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(savedReservation);
    }

    @Override
    public List<ReservationDto> getReservationsByDateAndSpecialist(LocalDate reservationDate, Long specialistId) {
        log.info("Getting reservations by date {} and specialistId {}", reservationDate, specialistId);
        List<Reservation> reservations = findReservationsByDateAndSpecialist(reservationDate, specialistId);
        return reservations.stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto updateReservation(Long id, ReservationDto updatedReservationDto) {
        log.info("Updating reservation with id {}", id);

        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Reservation not found with id " + id));

        updatedReservationDto.setId(id);
        var reservationDate = updatedReservationDto.getReservationTime().toLocalDate();
        var startTime = updatedReservationDto.getReservationTime();
        var endTime = updatedReservationDto.getReservationTime()
                .plusMinutes(updatedReservationDto.getProcedure().getDuration());

        List<Reservation> existingReservations = findReservationsByDateAndSpecialist(
                reservationDate, existingReservation.getSpecialist().getId());

        for (Reservation currentReservation : existingReservations) {
            if (currentReservation.getId().equals(id)) {
                continue; // Skip checking for the current reservation being updated
            }
            LocalDateTime currentReservationEndTime = currentReservation.getReservationTime()
                    .plusMinutes(currentReservation.getProcedure().getDuration());

            if (startTime.isBefore(currentReservationEndTime) && endTime.isAfter(currentReservation.getReservationTime())) {
                throw new ScheduleConflictException("Reservation cannot be updated due to schedule conflict.");
            }
        }

        Reservation savedReservation = reservationMapper.toEntity(updatedReservationDto);

        return reservationMapper.toDto(savedReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        log.info("Deleting reservation with id {}", id);
        checkIfReservationExist(id);
        reservationRepository.deleteById(id);
    }

    private List<Reservation> findReservationsByDateAndSpecialist(LocalDate reservationDate, Long specialistId) {
        return reservationRepository.findByReservationTimeAndSpecialist(reservationDate.atStartOfDay(),
                reservationDate.atTime(23, 59), specialistId);
    }

    private void checkIfReservationExist(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new DataNotFoundException(String.format("Reservation with id %s does not exist.", id));
        }
    }
}
