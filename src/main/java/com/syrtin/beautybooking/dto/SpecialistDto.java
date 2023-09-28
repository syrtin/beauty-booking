package com.syrtin.beautybooking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class SpecialistDto {
    private Long id;

    @NotNull(message = "Name must not be null")
    private String name;

    @NotNull(message = "Phone must not be null")
    private String phone;

    private Set<Long> procedureIds;
}