package com.syrtin.beautybooking.repository.extractor;

import com.syrtin.beautybooking.model.Client;
import com.syrtin.beautybooking.model.Procedure;
import com.syrtin.beautybooking.model.Reservation;
import com.syrtin.beautybooking.model.Specialist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtractorUtils {

    public static Reservation extract(ResultSet rs) throws SQLException {
        return new Reservation(
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
    }
}
