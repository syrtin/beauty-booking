package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.ProcedureDto;
import com.syrtin.beautybooking.exception.DataNotFoundException;
import com.syrtin.beautybooking.mapper.ProcedureMapper;
import com.syrtin.beautybooking.model.Procedure;
import com.syrtin.beautybooking.repository.ProcedureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ProcedureMapper procedureMapper;

    public ProcedureServiceImpl(ProcedureRepository procedureRepository, ProcedureMapper procedureMapper) {
        this.procedureRepository = procedureRepository;
        this.procedureMapper = procedureMapper;
    }

    @Override
    public List<ProcedureDto> getAllProcedures() {
        log.info("Getting all procedures");
        List<Procedure> procedures = procedureRepository.findAll();
        return procedures.stream()
                .map(procedureMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProcedureDto getProcedureById(Long id) {
        log.info("Getting procedure by id: {}", id);
        var procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Procedure with id %s not found", id)));
        return procedureMapper.toDto(procedure);
    }

    @Override
    public ProcedureDto createProcedure(ProcedureDto procedureDto) {
        log.info("Creating new procedure");
        var procedure = procedureMapper.toEntity(procedureDto);
        var createdProcedure = procedureRepository.save(procedure);
        return procedureMapper.toDto(createdProcedure);
    }

    @Override
    public ProcedureDto updateProcedure(Long id, ProcedureDto procedureDto) {
        log.info("Updating procedure with id: {}", id);
        checkIfProcedureExist(id);
        procedureDto.setId(id);
        var procedure = procedureMapper.toEntity(procedureDto);
        var updatedClient = procedureRepository.save(procedure);
        return procedureMapper.toDto(updatedClient);
    }

    @Override
    public void deleteProcedure(Long id) {
        log.info("Deleting procedure with id: {}", id);
        checkIfProcedureExist(id);
        procedureRepository.deleteById(id);
    }

    private void checkIfProcedureExist(Long id) {
        if (!procedureRepository.existsById(id)) {
            throw new DataNotFoundException(String.format("Procedure with id %s does not exist.", id));
        }
    }
}