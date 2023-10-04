package com.syrtin.beautybooking.model;

import jakarta.annotation.Nonnull;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Table("procedure")
public class Procedure {

    @Id
    private final Long id;

    @NonNull
    private final String name;

    @Nonnull
    private final Integer duration;

    @Nonnull
    private final Integer cost;

    @PersistenceCreator
    public Procedure(Long id, String name, Integer duration, Integer cost) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.cost = cost;
    }

    public Procedure(String name, Integer duration, Integer cost) {
        this(null, name, duration, cost);
    }

    public Procedure() {
        this(null, null, null, null);
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", cost=" + cost +
                '}';
    }
}