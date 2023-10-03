package com.syrtin.beautybooking.repository;

import com.syrtin.beautybooking.model.Reservation;
import com.syrtin.beautybooking.repository.extractor.ReservationListResultSetExtractor;
import com.syrtin.beautybooking.repository.extractor.ReservationOptionalResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query(value = "" +
            "select  r.id               as reservation_id," +
                    "r.reservation_time as reservation_time," +
                    "c.id               as client_id," +
                    "c.name             as client_name," +
                    "c.phone            as client_phone," +
                    "s.id               as specialist_id," +
                    "s.name             as specialist_name," +
                    "s.phone            as specialist_phone," +
                    "p.id               as procedure_id," +
                    "p.name             as procedure_name," +
                    "p.duration         as procedure_duration," +
                    "p.cost             as procedure_cost " +
            "from reservation r " +
            "left outer join client c       on  r.client_id = c.id " +
            "left outer join specialist s   on r.specialist_id = s.id " +
            "left outer join procedure p    on r.procedure_id = p.id " +
            "where r.reservation_time >= :startDate " +
            "and r.reservation_time <= :endDate and r.specialist_id = :specialistId",
            resultSetExtractorClass = ReservationListResultSetExtractor.class)
    List<Reservation> findByReservationInterfalAndSpecialist(LocalDateTime startDate,
                                                             LocalDateTime endDate,
                                                             Long specialistId);

    @Query(value = "" +
            "select r.id            as reservation_id," +
                "r.reservation_time as reservation_time," +
                "c.id               as client_id," +
                "c.name             as client_name," +
                "c.phone            as client_phone," +
                "s.id               as specialist_id," +
                "s.name             as specialist_name," +
                "s.phone            as specialist_phone," +
                "p.id               as procedure_id," +
                "p.name             as procedure_name," +
                "p.duration         as procedure_duration," +
                "p.cost             as procedure_cost " +
            "from reservation r " +
            "left outer join client c       on r.client_id = c.id " +
            "left outer join specialist s   on r.specialist_id = s.id " +
            "left outer join procedure p    on r.procedure_id = p.id " +
            "where r.reservation_time = :reservationTime and r.specialist_id = :specialistId",
            resultSetExtractorClass = ReservationOptionalResultSetExtractor.class)
    Optional<Reservation> findByReservationTimeAndSpecialist(LocalDateTime reservationTime, Long specialistId);

    @Modifying
    @Query("insert into reservation(reservation_time, client_id, specialist_id, procedure_id) " +
            "values (:reservationTime, :clientId, :specialistId, :procedureId)")
    void save(@Param("reservationTime") LocalDateTime reservationTime,
              @Param("clientId") Long clientId,
              @Param("specialistId") Long specialistId,
              @Param("procedureId") Long procedureId);

    @Modifying
    @Query("update reservation set reservation_time = :reservationTime, client_id = :clientId, " +
            "specialist_id = :specialistId, procedure_id = :procedureId where id = :id")
    void update(@Param("id") Long id,
                @Param("reservationTime") LocalDateTime reservationTime,
                @Param("clientId") Long clientId,
                @Param("specialistId") Long specialistId,
                @Param("procedureId") Long procedureId);

    @Modifying
    @Query("delete from reservation where reservation.id = :reservationId")
    void deleteReservationWithoutCascade(@Param("reservationId") Long reservationId);
}