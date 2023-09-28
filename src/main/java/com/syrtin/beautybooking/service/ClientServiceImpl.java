package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.ClientDto;
import com.syrtin.beautybooking.exception.DataNotFoundException;
import com.syrtin.beautybooking.mapper.ClientMapper;
import com.syrtin.beautybooking.model.Client;
import com.syrtin.beautybooking.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDto createClient(ClientDto clientDTO) {
        log.info("Creating new client");
        var client = clientMapper.toEntity(clientDTO);
        var savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    public List<ClientDto> getAllClients() {
        log.info("Getting all clients");
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto getClientById(Long id) {
        log.info("Getting client by id: {}", id);
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Client with id %s not found", id)));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto clientDTO) {
        log.info("Updating client with id: {}", id);
        checkIfClientExist(id);
        clientDTO.setId(id);
        var client = clientMapper.toEntity(clientDTO);
        var updatedClient = clientRepository.save(client);
        return clientMapper.toDto(updatedClient);
    }

    @Override
    public void deleteClient(Long id) {
        log.info("Deleting client with id: {}", id);
        checkIfClientExist(id);
        clientRepository.deleteById(id);
    }

    private void checkIfClientExist(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new DataNotFoundException(String.format("Client with id %s does not exist.", id));
        }
    }
}