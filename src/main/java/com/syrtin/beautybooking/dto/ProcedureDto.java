package com.syrtin.beautybooking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProcedureDto {
    private Long id;

    @NotNull(message = "Name must not be null")
    private String name;

    @NotNull(message = "Duration must not be null")
    private Integer duration;

    @NotNull(message = "Cost must not be null")
    private Integer cost;
}