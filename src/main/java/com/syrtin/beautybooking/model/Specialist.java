package com.syrtin.beautybooking.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table("specialists")
public class Specialist {
    @Id
    private final Long id;
    private final String name;
    private final String phone;

    public Specialist(String name, String phone) {
        this(null, name, phone);
    }
}