package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.ReservationDto;
import com.syrtin.beautybooking.exception.DataNotFoundException;
import com.syrtin.beautybooking.exception.OutOfSaloonWorkingHoursException;
import com.syrtin.beautybooking.exception.ScheduleConflictException;
import com.syrtin.beautybooking.mapper.ReservationMapper;
import com.syrtin.beautybooking.model.Reservation;
import com.syrtin.beautybooking.repository.ReservationRepository;
import com.syrtin.beautybooking.sessionmanager.TransactionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final TransactionManager transactionManager;


    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationMapper reservationMapper,
                                  TransactionManager transactionManager) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.transactionManager = transactionManager;
    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDTO) {
        log.info("Creating a new reservation");

        return reservationMapper.toDto(saveReservation(reservationMapper.toEntity(reservationDTO)));

    }

    @Override
    public ReservationDto updateReservation(Long id, ReservationDto updatedReservationDto) {
        log.info("Updating reservation with id {}", id);

        if (!reservationRepository.existsById(id)) {
            throw new DataNotFoundException("Reservation not found with id " + id);
        }

        updatedReservationDto.setId(id);

        return reservationMapper.toDto(saveReservation(reservationMapper.toEntity(updatedReservationDto)));
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

    private Reservation saveReservation(Reservation reservation) {
        return transactionManager.doInTransaction(() -> {
            var reservationDate = reservation.getReservationTime().toLocalDate();
            var startTime = reservation.getReservationTime();
            var endTime = reservation.getReservationTime().plusMinutes(reservation.getProcedure().getDuration());

            if (startTime.toLocalTime().isBefore(LocalTime.of(10, 0))
                    || endTime.toLocalTime().isAfter(LocalTime.of(22, 0))) {
                throw new OutOfSaloonWorkingHoursException("Reservation gap is out of saloon working hours");
            }

            var existingReservations = findReservationsByDateAndSpecialist(
                    reservationDate, reservation.getSpecialist().getId());

            for (Reservation currentReservation : existingReservations) {
                if (currentReservation.getId().equals(reservation.getId())) {
                    continue; // Skip checking for the current reservation being updated
                }
                var currentReservationEndTime = currentReservation.getReservationTime()
                        .plusMinutes(currentReservation.getProcedure().getDuration());
                if (startTime.isBefore(currentReservationEndTime) && endTime.isAfter(currentReservation.getReservationTime())) {
                    throw new ScheduleConflictException("Reservation cannot be saved due to schedule conflict.");
                }
            }

            return reservationRepository.save(reservation);
        });
    }
}
