package com.syrtin.beautybooking.mapper;

import com.syrtin.beautybooking.dto.SpecialistDto;
import com.syrtin.beautybooking.model.ProcedureRef;
import com.syrtin.beautybooking.model.Specialist;
import org.modelmapper.ModelMapper;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SpecialistMapper {
    private final ModelMapper modelMapper;

    public SpecialistMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SpecialistDto toDTO(Specialist specialist) {
        SpecialistDto dto = modelMapper.map(specialist, SpecialistDto.class);
        dto.setProcedureIds(specialist.getProcedureSet().stream()
                .map(ProcedureRef::getProcedureId)
                .map(AggregateReference::getId)
                .collect(Collectors.toSet()));
        return dto;
    }

    public Specialist toEntity(SpecialistDto dto) {
        return modelMapper.map(dto, Specialist.class);
    }
}

