package com.syrtin.beautybooking.repository;

import com.syrtin.beautybooking.model.Reservation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query("SELECT * FROM reservation r WHERE r.reservation_time >= :startDate " +
            "AND r.reservation_time <= :endDate AND r.specialist_id = :specialistId")
    List<Reservation> findByReservationTimeAndSpecialist(LocalDateTime startDate, LocalDateTime endDate, Long specialistId);
}