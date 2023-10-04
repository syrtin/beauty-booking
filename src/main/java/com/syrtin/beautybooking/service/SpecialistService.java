package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.SpecialistDto;

import java.util.List;

public interface SpecialistService {
    SpecialistDto createSpecialist(SpecialistDto specialist);

    SpecialistDto updateSpecialist(Long id, SpecialistDto specialist);

    List<SpecialistDto> getAllSpecialists();

    SpecialistDto getSpecialist(Long id);

    void deleteSpecialist(Long id);

    void addProcedure(Long specialistId, Long procedureId);

    void removeProcedure(Long specialistId, Long procedureId);

    List<SpecialistDto> getAllSpecialistsByProcedure(Long procedureId);
}
