package com.syrtin.beautybooking.mapper;

import com.syrtin.beautybooking.dto.ClientDto;
import com.syrtin.beautybooking.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    private final ModelMapper modelMapper;

    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClientDto toDto(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }

    public Client toEntity(ClientDto clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }
}