package com.syrtin.beautybooking.service;

import com.syrtin.beautybooking.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientDto clientDTO);

    ClientDto updateClient(Long id, ClientDto clientDTO);

    List<ClientDto> getAllClients();

    ClientDto getClientById(Long id);

    void deleteClient(Long id);
}
