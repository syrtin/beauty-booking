package com.syrtin.beautybooking.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table("procedures")
public class Procedure {
    @Id
    private final Long id;
    private final int duration;
    private final double cost;

    @MappedCollection(idColumn = "procedure_id", keyColumn = "specialist_id")
    private final Set<Specialist> specialists;

    public Procedure(int duration, double cost, Set<Specialist> specialists) {
        this(null, duration, cost, specialists);
    }
}