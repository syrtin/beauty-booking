package com.syrtin.beautybooking.mapper;

import com.syrtin.beautybooking.dto.ProcedureDto;
import com.syrtin.beautybooking.model.Procedure;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProcedureMapper {

    private final ModelMapper modelMapper;

    public ProcedureMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProcedureDto toDto(Procedure procedure) {
        return modelMapper.map(procedure, ProcedureDto.class);
    }

    public Procedure toEntity(ProcedureDto procedureDto) {
        return modelMapper.map(procedureDto, Procedure.class);
    }
}