package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.ProcedureDto;

import java.util.List;

public interface ProcedureService {
    List<ProcedureDto> getAllProcedures();

    ProcedureDto getProcedureById(Long id);

    ProcedureDto createProcedure(ProcedureDto procedureDto);

    ProcedureDto updateProcedure(Long id, ProcedureDto procedureDto);

    void deleteProcedure(Long id);
}