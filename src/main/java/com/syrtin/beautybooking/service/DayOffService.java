package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.DayOffDto;

import java.util.List;

public interface DayOffService {
    DayOffDto createDayOff(DayOffDto dayOffDto);

    DayOffDto getDayOffById(Long id);

    List<DayOffDto> getAllDayOffs();

    DayOffDto updateDayOff(Long id, DayOffDto dayOffDto);

    void deleteDayOff(Long id);
}
