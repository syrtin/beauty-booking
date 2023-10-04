package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.DayOffDto;
import com.syrtin.beautybooking.exception.DataNotFoundException;
import com.syrtin.beautybooking.mapper.DayOffMapper;
import com.syrtin.beautybooking.model.DayOff;
import com.syrtin.beautybooking.repository.DayOffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DayOffServiceImpl implements DayOffService {

    private final DayOffRepository dayOffRepository;
    private final DayOffMapper dayOffMapper;

    public DayOffServiceImpl(DayOffRepository dayOffRepository, DayOffMapper dayOffMapper) {
        this.dayOffRepository = dayOffRepository;
        this.dayOffMapper = dayOffMapper;
    }

    public DayOffDto createDayOff(DayOffDto dayOffDto) {
        log.info("Creating DayOff: {}", dayOffDto);

        var dayOff = dayOffMapper.toEntity(dayOffDto);
        var savedDayOff = dayOffRepository.save(dayOff);

        log.info("Created DayOff: {}", savedDayOff);

        return dayOffMapper.toDto(savedDayOff);
    }

    public DayOffDto getDayOffById(Long id) {
        log.info("Getting DayOff with id: {}", id);

        var dayOff = dayOffRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("DayOff not found with id: " + id));

        log.info("Got DayOff: {}", dayOff);

        return dayOffMapper.toDto(dayOff);
    }

    public List<DayOffDto> getAllDayOffs() {
        log.info("Getting all DayOffs");

        var dayOffList = (List<DayOff>) dayOffRepository.findAll();
        var dayOffDtos = dayOffList.stream()
                .map(dayOffMapper::toDto)
                .collect(Collectors.toList());

        log.info("Got {} DayOffs", dayOffList.size());

        return dayOffDtos;
    }

    public DayOffDto updateDayOff(Long id, DayOffDto dayOffDto) {
        log.info("Updating DayOff with id: {}, data: {}", id, dayOffDto);

        checkIfDayOffExist(id);
        dayOffDto.setId(id);
        var dayOff = dayOffMapper.toEntity(dayOffDto);
        var updatedDayOff = dayOffRepository.save(dayOff);

        log.info("Updated DayOff: {}", updatedDayOff);

        return dayOffMapper.toDto(updatedDayOff);
    }

    public void deleteDayOff(Long id) {
        log.info("Deleting DayOff with id: {}", id);

        dayOffRepository.deleteById(id);

        log.info("Deleted DayOff with id: {}", id);
    }

    private void checkIfDayOffExist(Long id) {
        if (!dayOffRepository.existsById(id)) {
            throw new DataNotFoundException(String.format("DayOff with id %s does not exist.", id));
        }
    }
}