package com.syrtin.beautybooking.repository.extractor;

import com.syrtin.beautybooking.model.Client;
import com.syrtin.beautybooking.model.Procedure;
import com.syrtin.beautybooking.model.Reservation;
import com.syrtin.beautybooking.model.Specialist;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReservationOptionalResultSetExtractor implements ResultSetExtractor<Optional<Reservation>> {

    @Override
    public Optional<Reservation> extractData(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Reservation reservation = new Reservation(
                    rs.getLong("reservation_id"),
                    rs.getTimestamp("reservation_time").toLocalDateTime(),
                    new Client(
                            rs.getLong("client_id"),
                            rs.getString("client_name"),
                            rs.getString("client_phone")
                    ),
                    new Specialist(
                            rs.getLong("specialist_id"),
                            rs.getString("specialist_name"),
                            rs.getString("specialist_phone")
                    ),
                    new Procedure(
                            rs.getLong("procedure_id"),
                            rs.getString("procedure_name"),
                            rs.getInt("procedure_duration"),
                            rs.getInt("procedure_cost")
                    )
            );
            return Optional.of(reservation);
        } else {
            return Optional.empty();
        }
    }
}