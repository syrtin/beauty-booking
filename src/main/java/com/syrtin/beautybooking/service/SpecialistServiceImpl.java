package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.SpecialistDto;
import com.syrtin.beautybooking.exception.DataNotFoundException;
import com.syrtin.beautybooking.mapper.SpecialistMapper;
import com.syrtin.beautybooking.model.ProcedureRef;
import com.syrtin.beautybooking.model.Specialist;
import com.syrtin.beautybooking.repository.ProcedureRepository;
import com.syrtin.beautybooking.repository.SpecialistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SpecialistServiceImpl implements SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final ProcedureRepository procedureRepository;
    private final SpecialistMapper specialistMapper;

    public SpecialistServiceImpl(SpecialistRepository specialistRepository, ProcedureRepository procedureRepository, SpecialistMapper specialistMapper) {
        this.specialistRepository = specialistRepository;
        this.procedureRepository = procedureRepository;
        this.specialistMapper = specialistMapper;
    }


    public SpecialistDto createSpecialist(SpecialistDto specialistDTO) {
        log.info("Creating specialist: {}", specialistDTO);

        Specialist specialist = specialistMapper.toEntity(specialistDTO);
        specialist = specialistRepository.save(specialist);

        log.info("Created specialist: {}", specialist);

        return specialistMapper.toDTO(specialist);
    }

    public SpecialistDto getSpecialist(Long id) {
        log.info("Getting specialist with id: {}", id);

        Specialist specialist = specialistRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Specialist not found with id: " + id));

        log.info("Got specialist: {}", specialist);

        return specialistMapper.toDTO(specialist);
    }

    public List<SpecialistDto> getAllSpecialists() {
        log.info("Getting all specialists");

        List<Specialist> specialists = (List<Specialist>) specialistRepository.findAll();
        List<SpecialistDto> specialistDtos = specialists.stream()
                .map(specialistMapper::toDTO)
                .collect(Collectors.toList());

        log.info("Got {} specialists", specialists.size());

        return specialistDtos;
    }

    public SpecialistDto updateSpecialist(Long id, SpecialistDto specialistDTO) {
        log.info("Updating specialist with id: {}, data: {}", id, specialistDTO);

        checkIfSpecialistExist(id);
        var updatedSpecialist = new Specialist(id, specialistDTO.getName(), specialistDTO.getPhone());

        Set<ProcedureRef> procedureSet = new HashSet<>();
        if (specialistDTO.getProcedureIds() != null) {
            for (Long procedureId : specialistDTO.getProcedureIds()) {
                if (!procedureRepository.existsById(procedureId)) {
                    throw new DataNotFoundException("Procedure not found with id: " + id);
                }
                ProcedureRef procedureRef = new ProcedureRef();
                procedureRef.setProcedureId(AggregateReference.to(procedureId));
                procedureSet.add(procedureRef);
            }
        }

        updatedSpecialist.setProcedureSet(procedureSet);

        var savedSpecialist = specialistRepository.save(updatedSpecialist);

        log.info("Updated specialist: {}", savedSpecialist);

        return specialistMapper.toDTO(savedSpecialist);
    }

    public void deleteSpecialist(Long id) {
        log.info("Deleting specialist with id: {}", id);

        checkIfSpecialistExist(id);
        specialistRepository.deleteById(id);

        log.info("Deleted specialist with id: {}", id);
    }

    public void addProcedure(Long specialistId, Long procedureId) {
        log.info("Adding procedure with id: {} to specialist with id: {}", procedureId, specialistId);

        var specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new DataNotFoundException("Specialist not found with id: " + specialistId));
        var procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new DataNotFoundException("Procedure not found with id: " + procedureId));

        specialist.addProcedure(procedure);
        specialistRepository.save(specialist);

        log.info("Added procedure with id: {} to specialist with id: {}", procedureId, specialistId);
    }

    public void removeProcedure(Long specialistId, Long procedureId) {
        log.info("Removing procedure with id: {} from specialist with id: {}", procedureId, specialistId);

        var specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new DataNotFoundException("Specialist not found with id: " + specialistId));
        var procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new DataNotFoundException("Procedure not found with id: " + procedureId));

        try {
            specialist.removeProcedure(procedure);
        } catch (Exception e) {
            throw new DataNotFoundException("Specialist does not have procedure with id: " + procedureId);
        }
        specialistRepository.save(specialist);

        log.info("Removed procedure with id: {} from specialist with id: {}", procedureId, specialistId);
    }

    @Override
    public List<SpecialistDto> getAllSpecialistsByProcedure(Long procedureId) {
        log.info("Getting all specialists by procedure" + procedureId);

        List<Specialist> specialists = specialistRepository.findAllByProcedure(procedureId);
        List<SpecialistDto> specialistDtos = specialists.stream()
                .map(specialistMapper::toDTO)
                .collect(Collectors.toList());

        log.info("Got {} specialists by Procedure", specialists.size());

        return specialistDtos;
    }

    private void checkIfSpecialistExist(Long id) {
        if (!specialistRepository.existsById(id)) {
            throw new DataNotFoundException(String.format("Reservation with id %s does not exist.", id));
        }
    }
}