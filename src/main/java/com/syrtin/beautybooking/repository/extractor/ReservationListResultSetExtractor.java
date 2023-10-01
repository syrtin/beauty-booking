package com.syrtin.beautybooking.repository.extractor;

import com.syrtin.beautybooking.model.Client;
import com.syrtin.beautybooking.model.Procedure;
import com.syrtin.beautybooking.model.Reservation;
import com.syrtin.beautybooking.model.Specialist;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationListResultSetExtractor implements ResultSetExtractor<List<Reservation>> {

    @Override
    public List<Reservation> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Reservation> reservations = new ArrayList<>();
        while (rs.next()) {
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
            reservations.add(reservation);
        }
        return reservations;
    }
}
