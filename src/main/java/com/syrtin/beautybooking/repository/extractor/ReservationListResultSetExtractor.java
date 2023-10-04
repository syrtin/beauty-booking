package com.syrtin.beautybooking.repository.extractor;

import com.syrtin.beautybooking.model.Reservation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.syrtin.beautybooking.repository.extractor.ExtractorUtils.extract;

public class ReservationListResultSetExtractor implements ResultSetExtractor<List<Reservation>> {

    @Override
    public List<Reservation> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Reservation> reservations = new ArrayList<>();
        while (rs.next()) {
            var reservation = extract(rs);
            reservations.add(reservation);
        }
        return reservations;
    }
}
