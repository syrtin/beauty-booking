package com.syrtin.beautybooking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDto {
    private Long id;

    @NotNull(message = "Reservation time must not be null")
    @Future(message = "Reservation time must be a future date/time")
    private LocalDateTime reservationTime;

    @NotNull(message = "Client must not be null")
    private ClientDto client;

    @NotNull(message = "Specialist must not be null")
    private SpecialistDto specialist;

    @NotNull(message = "Procedure must not be null")
    private ProcedureDto procedure;
}