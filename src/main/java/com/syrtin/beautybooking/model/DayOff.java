package com.syrtin.beautybooking.model;

import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("day_off")
public class DayOff {

    @Id
    private final Long id;

    @NonNull
    private final LocalDate dayOffDate;

    @NonNull
    private final String reason;

    @NonNull
    @MappedCollection(idColumn = "specialist_id", keyColumn = "reservation_id")
    private final Long specialistId;

    @PersistenceCreator
    public DayOff(Long id, @NonNull LocalDate dayOffDate, @NonNull String reason, Long specialistId) {
        this.id = id;
        this.dayOffDate = dayOffDate;
        this.reason = reason;
        this.specialistId = specialistId;
    }

    public DayOff(@NonNull LocalDate dayOffDate, @NonNull String reason, Long specialistId) {
        this(null, dayOffDate, reason, specialistId);
    }

    public DayOff() {
        this(null, null, null, null);
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDayOffDate() {
        return dayOffDate;
    }

    public String getReason() {
        return reason;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    @Override
    public String toString() {
        return "DayOff{" +
                "id=" + id +
                ", dayOffDate=" + dayOffDate +
                ", reason='" + reason + '\'' +
                ", specialistId=" + specialistId +
                '}';
    }
}
