package com.syrtin.beautybooking.model;

import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("reservation")
public class Reservation {
    @Id
    private final Long id;

    @NonNull
    private final LocalDateTime reservationTime;

    @NonNull
    @MappedCollection(idColumn = "client_id", keyColumn = "reservation_id")
    private final Client client;

    @NonNull
    @MappedCollection(idColumn = "specialist_id", keyColumn = "reservation_id")
    private final Specialist specialist;

    @NonNull
    @MappedCollection(idColumn = "procedure_id", keyColumn = "reservation_id")
    private final Procedure procedure;

    @PersistenceCreator
    public Reservation(Long id, LocalDateTime reservationTime, Client client, Specialist specialist, Procedure procedure) {
        this.id = id;
        this.reservationTime = reservationTime;
        this.client = client;
        this.specialist = specialist;
        this.procedure = procedure;
    }

    public Reservation(LocalDateTime reservationTime, Client client, Specialist specialist, Procedure procedure) {
        this(null, reservationTime, client, specialist, procedure);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public Client getClient() {
        return client;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationTime=" + reservationTime +
                ", client=" + client +
                ", specialist=" + specialist +
                ", procedure=" + procedure +
                '}';
    }
}
