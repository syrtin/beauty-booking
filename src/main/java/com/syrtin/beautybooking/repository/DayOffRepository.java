package com.syrtin.beautybooking.repository;

import com.syrtin.beautybooking.model.DayOff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DayOffRepository extends CrudRepository<DayOff, Long> {

    Optional<DayOff> findDayOffByDayOffDateAndSpecialistId(LocalDate dayOffDate, Long specialistId);
}