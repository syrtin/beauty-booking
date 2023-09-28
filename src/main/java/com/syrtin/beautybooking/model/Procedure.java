package com.syrtin.beautybooking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Table("procedure")
public class Procedure {

    @Id
    private final Long id;

    private final Integer duration;

    private final Integer cost;

    @PersistenceCreator
    public Procedure(Long id, Integer duration, Integer cost) {
        this.id = id;
        this.duration = duration;
        this.cost = cost;
    }

    public Procedure(Integer duration, Integer cost) {
        this(null, duration, cost);
    }

    public Procedure() {
        this.id = null;
        this.duration = null;
        this.cost = null;
    }


    public Long getId() {
        return id;
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
                ", duration=" + duration +
                ", cost=" + cost +
                '}';
    }
}