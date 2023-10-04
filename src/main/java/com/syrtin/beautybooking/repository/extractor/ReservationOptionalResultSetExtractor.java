package com.syrtin.beautybooking.repository.extractor;

import com.syrtin.beautybooking.model.Client;
import com.syrtin.beautybooking.model.Procedure;
import com.syrtin.beautybooking.model.Reservation;
import com.syrtin.beautybooking.model.Specialist;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.syrtin.beautybooking.repository.extractor.ExtractorUtils.extract;

public class ReservationOptionalResultSetExtractor implements ResultSetExtractor<Optional<Reservation>> {

    @Override
    public Optional<Reservation> extractData(ResultSet rs) throws SQLException {
        if (rs.next()) {
            var reservation = extract(rs);
            return Optional.of(reservation);
        } else {
            return Optional.empty();
        }
    }
}