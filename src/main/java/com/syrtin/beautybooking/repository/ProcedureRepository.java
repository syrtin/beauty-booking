package com.syrtin.beautybooking.repository;

import com.syrtin.beautybooking.model.Procedure;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends CrudRepository<Procedure, Long> {

    @Query("SELECT * FROM procedure")
    List<Procedure> findAll();

    @Modifying
    @Query("DELETE FROM procedure WHERE id = :id")
    void deleteById(Long id);
}