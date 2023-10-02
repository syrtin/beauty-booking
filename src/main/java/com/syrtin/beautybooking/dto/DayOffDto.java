package com.syrtin.beautybooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DayOffDto {

    private Long id;

    @NotNull
    private LocalDate dayOffDate;

    @NotBlank
    @Size(max = 255)
    private String reason;

    @NotNull(message = "Specialist must not be null")
    private Long specialistId;
}