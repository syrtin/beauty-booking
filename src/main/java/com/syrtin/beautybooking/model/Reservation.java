package com.syrtin.beautybooking.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table("reservations")
public class Reservation {
    @Id
    private final Long id;
    private final LocalDateTime reservationTime;

    @MappedCollection(idColumn = "reservation_id", keyColumn = "client_id")
    private final Client client;

    @MappedCollection(idColumn = "reservation_id", keyColumn = "specialist_id")
    private final Specialist specialist;

    public Reservation(LocalDateTime reservationTime, Client client, Specialist specialist) {
        this(null, reservationTime, client, specialist);
    }
}