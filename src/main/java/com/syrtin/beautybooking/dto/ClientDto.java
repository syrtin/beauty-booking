package com.syrtin.beautybooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ClientDto {
    private Long id;

    @NotBlank(message = "Name must not be null")
    private String name;

    @NotBlank(message = "Phone must not be null")
    private String phone;
}
