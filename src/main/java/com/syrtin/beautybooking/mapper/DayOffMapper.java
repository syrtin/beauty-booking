package com.syrtin.beautybooking.mapper;

import com.syrtin.beautybooking.dto.DayOffDto;
import com.syrtin.beautybooking.model.DayOff;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DayOffMapper {

    private final ModelMapper modelMapper;

    public DayOffMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DayOffDto toDto(DayOff dayOff) {
        return modelMapper.map(dayOff, DayOffDto.class);
    }

    public DayOff toEntity(DayOffDto dayOffDto) {
        return modelMapper.map(dayOffDto, DayOff.class);
    }
}