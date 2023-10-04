package com.syrtin.beautybooking.mapper;

import com.syrtin.beautybooking.dto.ReservationDto;
import com.syrtin.beautybooking.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;

    public ReservationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ReservationDto toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    public Reservation toEntity(ReservationDto reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }
}